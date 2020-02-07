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

class EventListAdapter(private val itemClickListener: View.OnClickListener) :
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
    private val clickListener: View.OnClickListener
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
        itemView.setOnClickListener(clickListener)

        eventName.text = model.eventName
        eventDesc.text = model.eventDesc
        eventLocation.text = model.eventLocation

        /* TODO: Temporary for now. Make visible based on number of attendees eventually */
        numAttendeesTextView.visibility = View.GONE
    }
}