package graeme.hosford.eventmanager.presentation.event.list.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.presentation.common.view.recyclerview.BaseAdapter
import graeme.hosford.eventmanager.presentation.common.view.recyclerview.BaseViewHolder
import graeme.hosford.eventmanager.presentation.event.list.model.EventListItemUiModel
import graeme.hosford.eventmanager.presentation.utils.DatePresentationUtils
import graeme.hosford.eventmanager.presentation.utils.PeoplePresentationUtils

class EventListAdapter(private val itemPresenterBridge: EventListItemPresenterBridge) :
    BaseAdapter<EventListItemUiModel, EventListItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventListItemViewHolder {
        return EventListItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.event_list_item,
                parent,
                false
            ),
            itemPresenterBridge
        )
    }
}

class EventListItemViewHolder(
    itemView: View,
    private val presenterBridge: EventListItemPresenterBridge
) : BaseViewHolder<EventListItemUiModel>(itemView) {

    @BindView(R.id.event_list_item_name_text_view)
    lateinit var eventName: TextView

    @BindView(R.id.event_list_item_description_text_view)
    lateinit var eventDesc: TextView

    @BindView(R.id.event_list_item_date_text_view)
    lateinit var eventDate: TextView

    @BindView(R.id.event_list_item_time_text_view)
    lateinit var eventTime: TextView

    @BindView(R.id.event_list_item_location_text_view)
    lateinit var eventLocation: TextView

    @BindView(R.id.event_list_item_num_attendees_text_view)
    lateinit var numAttendeesTextView: TextView

    @BindView(R.id.event_response_options_container)
    lateinit var responseContainer: LinearLayout

    @BindView(R.id.event_list_item_going_response_text_view)
    lateinit var goingTextView: TextView

    @BindView(R.id.event_list_item_not_going_response_text_view)
    lateinit var notGoingTextView: TextView

    override fun bind(model: EventListItemUiModel) {
        ButterKnife.bind(this, itemView)
        itemView.setOnClickListener {
            presenterBridge.onEventListItemClick(model.id)
        }

        eventName.text = model.eventName
        eventDesc.text = model.eventDesc

        eventDate.text = DatePresentationUtils.formatDateRange(model.startDate, model.endDate)
        eventTime.text = DatePresentationUtils.formatTimeRange(model.startDate, model.endDate)

        eventLocation.text = model.eventLocation

        val attendees = model.attendees

        if (attendees.size == 0) {
            numAttendeesTextView.visibility = View.INVISIBLE
        } else if (attendees.size > 0) {
            numAttendeesTextView.visibility = View.VISIBLE
            numAttendeesTextView.text =
                PeoplePresentationUtils.getAttendeeSummary(itemView.context.resources, attendees)
        }

        /* Commenting this out for the sake of being able to demo new functionality.
        * Normally the person creating the event would not be able to respond to it as
        * going or not going, they would be considered automatically going  */
        if (/*presenterBridge.getCurrentUserId() == model.eventOwner ||*/
            presenterBridge.getCurrentUserId() in attendees
        ) {
            responseContainer.visibility = View.GONE
        }

        goingTextView.setOnClickListener {
            presenterBridge.onGoingResponseClick(model.id)
        }

        notGoingTextView.setOnClickListener {
            presenterBridge.onNotGoingResponseClick(model.id)
        }
    }
}