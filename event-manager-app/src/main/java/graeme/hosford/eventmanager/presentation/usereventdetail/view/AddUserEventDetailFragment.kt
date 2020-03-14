package graeme.hosford.eventmanager.presentation.usereventdetail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import graeme.hosford.eventmanager.EventManagerApplication
import graeme.hosford.eventmanager.databinding.FragmentAddUserEventDetailBinding
import graeme.hosford.eventmanager.presentation.common.view.fragment.BaseFragment
import graeme.hosford.eventmanager.presentation.company.detail.MEMBER_ID_ARG
import graeme.hosford.eventmanager.presentation.event.detail.EVENT_ID_ARG
import graeme.hosford.eventmanager.presentation.usereventdetail.AddUserEventDetailPresenter
import graeme.hosford.eventmanager.presentation.usereventdetail.AddUserEventDetailView
import javax.inject.Inject

class AddUserEventDetailFragment : BaseFragment(),
    AddUserEventDetailView {

    @Inject
    lateinit var presenter: AddUserEventDetailPresenter

    private var binding: FragmentAddUserEventDetailBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        EventManagerApplication.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        presenter.onViewCreated(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddUserEventDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.confirmUserEventDetailButton?.setOnClickListener {
            presenter.onConfirmDetailsButtonClick(
                arguments?.getString(MEMBER_ID_ARG)!!,
                arguments?.getString(EVENT_ID_ARG)!!,
                binding?.enterEventDetailSubject?.text.toString(),
                binding?.enterEventDetailEditText?.text.toString()
            )
        }
    }

    override fun onDetailsSaved() {
        findNavController().popBackStack()
    }
}