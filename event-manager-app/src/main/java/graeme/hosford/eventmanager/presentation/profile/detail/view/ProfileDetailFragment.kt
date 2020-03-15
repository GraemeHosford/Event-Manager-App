package graeme.hosford.eventmanager.presentation.profile.detail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.google.firebase.storage.FirebaseStorage
import graeme.hosford.eventmanager.EventManagerApplication
import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.databinding.FragmentProfileDetailBinding
import graeme.hosford.eventmanager.presentation.CoreIntents
import graeme.hosford.eventmanager.presentation.GlideApp
import graeme.hosford.eventmanager.presentation.common.view.custom.SummaryTextView
import graeme.hosford.eventmanager.presentation.common.view.fragment.BaseFragment
import graeme.hosford.eventmanager.presentation.company.detail.DEFAULT_MEMBER_ID_ARG
import graeme.hosford.eventmanager.presentation.company.detail.MEMBER_ID_ARG
import graeme.hosford.eventmanager.presentation.profile.detail.ProfileDetailPresenter
import graeme.hosford.eventmanager.presentation.profile.detail.ProfileDetailView
import graeme.hosford.eventmanager.presentation.profile.detail.model.ProfileDetailUiModel
import javax.inject.Inject

class ProfileDetailFragment : BaseFragment(), ProfileDetailView {

    @Inject
    lateinit var presenter: ProfileDetailPresenter

    private var binding: FragmentProfileDetailBinding? = null
    private val safeBinding get() = binding!!

    private lateinit var personId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        EventManagerApplication.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        presenter.onViewCreated(this)

        personId = arguments?.getString(MEMBER_ID_ARG) ?: DEFAULT_MEMBER_ID_ARG
        presenter.getPersonDetail(personId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileDetailBinding.inflate(inflater, container, false)
        return safeBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun setData(model: ProfileDetailUiModel) {
        safeBinding.profileDetailNameTextView.setDescriptionText(model.name)

        if (model.jobTitle.isNotBlank()) {
            safeBinding.profileDetailJobTitleTextView.setDescriptionText(model.jobTitle)
        } else {
            safeBinding.profileDetailJobTitleTextView.visibility = View.GONE
        }

        if (model.description.isNotBlank()) {
            safeBinding.profileDetailDescriptionTextView.setDescriptionText(model.description)
        } else {
            safeBinding.profileDetailDescriptionTextView.visibility = View.GONE
        }

        safeBinding.profileDetailUserEmailTextView.setDescriptionText(model.email)

        safeBinding.profileDetailUserEmailTextView.setOnClickListener {
            CoreIntents.sendEmailIntent(
                requireContext(),
                (it as SummaryTextView).getDescriptionText()
            )
        }

        safeBinding.viewUserEventDetailsTextView.setOnClickListener {
            findNavController().navigate(
                R.id.nav_user_event_detail_list,
                bundleOf(MEMBER_ID_ARG to personId)
            )
        }

        val imageRef = FirebaseStorage.getInstance().getReference(model.imageUrl)

        GlideApp.with(this)
            .load(imageRef)
            .placeholder(R.drawable.ic_person)
            .error(R.drawable.ic_person)
            .into(safeBinding.profileDetailPictureImageView)
    }
}