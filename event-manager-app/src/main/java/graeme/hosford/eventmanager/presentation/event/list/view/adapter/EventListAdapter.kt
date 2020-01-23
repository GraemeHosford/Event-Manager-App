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

class EventListAdapter : BaseAdapter<EventListItemUiModel, EventListItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventListItemViewHolder {
        return EventListItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.event_list_item,
                parent,
                false
            )
        )
    }
}

class EventListItemViewHolder(itemView: View) : BaseViewHolder<EventListItemUiModel>(itemView) {

    @BindView(R.id.event_list_item_name_text_view)
    lateinit var eventName: TextView

    override fun bind(model: EventListItemUiModel) {
        ButterKnife.bind(this, itemView)

        eventName.text = model.eventName
    }
}