package graeme.hosford.eventmanager.presentation.event.detail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import graeme.hosford.eventmanager.EventManagerApplication
import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.databinding.FragmentEventDetailBinding
import graeme.hosford.eventmanager.presentation.common.view.fragment.BaseFragment
import graeme.hosford.eventmanager.presentation.event.detail.ATTENDEES_ARG
import graeme.hosford.eventmanager.presentation.event.detail.EVENT_ID_ARG
import graeme.hosford.eventmanager.presentation.event.detail.EventDetailPresenter
import graeme.hosford.eventmanager.presentation.event.detail.EventDetailView
import graeme.hosford.eventmanager.presentation.event.detail.model.EventDetailUiModel
import graeme.hosford.eventmanager.presentation.event.list.common.EventListView
import graeme.hosford.eventmanager.presentation.utils.DatePresentationUtils
import graeme.hosford.eventmanager.presentation.utils.PeoplePresentationUtils
import javax.inject.Inject

class EventDetailFragment : BaseFragment(), EventDetailView {

    @Inject
    lateinit var presenter: EventDetailPresenter

    private var binding: FragmentEventDetailBinding? = null
    private val safeBinding get() = binding!!

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
        binding = FragmentEventDetailBinding.inflate(inflater, container, false)
        return safeBinding.root
    }

    override fun onResume() {
        super.onResume()
        presenter.loadEventDetail(arguments?.getString(EventListView.ARG_EVENT_ID)!!)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun setData(model: EventDetailUiModel) {
        safeBinding.eventDetailEventNameTextView.text = model.name
        safeBinding.eventDetailEventDescTextView.text = model.description
        safeBinding.eventDetailEventDateTextView.text =
            DatePresentationUtils.formatDateRange(model.startDate, model.endDate)
        safeBinding.eventDetailEventTimeTextView.text =
            DatePresentationUtils.formatTimeRange(model.startDate, model.endDate)

        if (context != null) {
            safeBinding.eventDetailEventAttendeesSummaryTextView.text =
                PeoplePresentationUtils.getAttendeeSummary(resources, model.attendees)

            safeBinding.eventDetailEventAttendeesSummaryTextView.setOnClickListener {
                findNavController().navigate(
                    R.id.nav_attendee_detail,
                    bundleOf(
                        EVENT_ID_ARG to model.id,
                        ATTENDEES_ARG to model.attendees
                    )
                )
            }
        }

        safeBinding.eventDetailEventLocationTextView.text = model.location
    }
}