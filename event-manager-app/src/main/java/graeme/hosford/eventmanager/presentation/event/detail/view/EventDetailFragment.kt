package graeme.hosford.eventmanager.presentation.event.detail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import graeme.hosford.eventmanager.EventManagerApplication
import graeme.hosford.eventmanager.databinding.FragmentEventDetailBinding
import graeme.hosford.eventmanager.presentation.common.view.fragment.BaseFragment
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

        presenter.loadEventDetail(arguments?.getString(EventListView.ARG_EVENT_ID)!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEventDetailBinding.inflate(inflater, container, false)
        return safeBinding.root
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
        }

        safeBinding.eventDetailEventLocationTextView.text = model.location
    }
}