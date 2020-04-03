package graeme.hosford.eventmanager.presentation.feedback.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import graeme.hosford.eventmanager.EventManagerApplication
import graeme.hosford.eventmanager.databinding.FragmentFeedbackBinding
import graeme.hosford.eventmanager.presentation.common.view.fragment.BaseFragment
import graeme.hosford.eventmanager.presentation.feedback.FeedbackPresenter
import graeme.hosford.eventmanager.presentation.feedback.FeedbackView
import javax.inject.Inject

class FeedbackFragment : BaseFragment(), FeedbackView {

    @Inject
    lateinit var presenter: FeedbackPresenter

    private var binding: FragmentFeedbackBinding? = null

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
        binding = FragmentFeedbackBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.submitFeedbackButton?.setOnClickListener {
            presenter.onSubmitFeedbackButtonClick(
                binding!!.eventCreationRatingBar.rating,
                binding!!.eventManagementRatingBar.rating,
                binding!!.attendeeManagementRatingBar.rating,
                binding!!.otherCommentsEditText.text.toString()
            )

            findNavController().popBackStack()
        }
    }
}