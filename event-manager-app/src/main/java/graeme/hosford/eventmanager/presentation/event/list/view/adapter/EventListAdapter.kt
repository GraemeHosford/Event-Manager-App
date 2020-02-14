package graeme.hosford.eventmanager.presentation.event.list.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.presentation.common.view.recyclerview.BaseAdapter
import graeme.hosford.eventmanager.presentation.common.view.recyclerview.BaseViewHolder
import graeme.hosford.eventmanager.presentation.event.list.model.EventListItemUiModel
import graeme.hosford.eventmanager.presentation.utils.PeoplePresentationUtils

class EventListAdapter(private val itemClickListener: EventListItemClickListener) :
    BaseAdapter<EventListItemUiModel, EventListItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventListItemViewHolder {
        return EventListItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.event_list_item,
                parent,
                false
            ),
            itemClickListener
        )
    }
}

class EventListItemViewHolder(
    itemView: View,
    private val clickListener: EventListItemClickListener
) : BaseViewHolder<EventListItemUiModel>(itemView) {

    @BindView(R.id.event_list_item_name_text_view)
    lateinit var eventName: TextView

    @BindView(R.id.event_list_item_description_text_view)
    lateinit var eventDesc: TextView

    @BindView(R.id.event_list_item_location_text_view)
    lateinit var eventLocation: TextView

    @BindView(R.id.event_list_item_num_attendees_text_view)
    lateinit var numAttendeesTextView: TextView

    override fun bind(model: EventListItemUiModel) {
        ButterKnife.bind(this, itemView)
        itemView.setOnClickListener {
            clickListener.onEventListItemClick(model.id)
        }

        eventName.text = model.eventName
        eventDesc.text = model.eventDesc
        eventLocation.text = model.eventLocation

        val attendees = model.attendees

        if (attendees.size == 0) {
            numAttendeesTextView.visibility = View.INVISIBLE
        } else if (attendees.size > 0) {
            numAttendeesTextView.visibility = View.VISIBLE
            numAttendeesTextView.text =
                PeoplePresentationUtils.getAttendeeSummary(itemView.context.resources, attendees)
        }
    }
}