package graeme.hosford.eventmanager.presentation.event.detail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import graeme.hosford.eventmanager.EventManagerApplication
import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.presentation.common.view.fragment.BaseFragment
import graeme.hosford.eventmanager.presentation.event.detail.EventDetailPresenter
import graeme.hosford.eventmanager.presentation.event.detail.EventDetailView
import graeme.hosford.eventmanager.presentation.event.detail.model.EventDetailUiModel
import graeme.hosford.eventmanager.presentation.event.list.EventListView
import graeme.hosford.eventmanager.presentation.utils.DatePresentationUtils
import graeme.hosford.eventmanager.presentation.utils.PeoplePresentationUtils
import javax.inject.Inject

class EventDetailFragment : BaseFragment(), EventDetailView {

    @Inject
    lateinit var presenter: EventDetailPresenter

    @BindView(R.id.event_detail_event_name_text_view)
    lateinit var eventName: TextView

    @BindView(R.id.event_detail_event_desc_text_view)
    lateinit var eventDesc: TextView

    @BindView(R.id.event_detail_event_date_text_view)
    lateinit var eventDate: TextView

    @BindView(R.id.event_Detail_event_time_text_view)
    lateinit var eventTime: TextView

    @BindView(R.id.event_detail_event_attendees_summary_text_view)
    lateinit var eventAttendees: TextView

    @BindView(R.id.event_detail_event_location_text_view)
    lateinit var eventLocation: TextView

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
        val view = inflater.inflate(R.layout.fragment_event_detail, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    override fun setData(model: EventDetailUiModel) {
        eventName.text = model.name
        eventDesc.text = model.description
        eventDate.text = DatePresentationUtils.formatDateRange(model.startDate, model.endDate)
        eventTime.text = DatePresentationUtils.formatTimeRange(model.startDate, model.endDate)

        if (context != null) {
            eventAttendees.text =
                PeoplePresentationUtils.getAttendeeSummary(resources, model.attendees)
        }

        eventLocation.text = model.location
    }
}