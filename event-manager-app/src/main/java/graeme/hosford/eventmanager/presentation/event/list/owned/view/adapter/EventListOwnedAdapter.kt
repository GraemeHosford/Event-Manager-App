package graeme.hosford.eventmanager.presentation.event.list.owned.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.databinding.EventListItemBinding
import graeme.hosford.eventmanager.presentation.common.view.recyclerview.BaseAdapter
import graeme.hosford.eventmanager.presentation.common.view.recyclerview.BaseViewHolder
import graeme.hosford.eventmanager.presentation.event.list.common.model.EventListItemUiModel
import graeme.hosford.eventmanager.presentation.utils.DatePresentationUtils
import graeme.hosford.eventmanager.presentation.utils.PeoplePresentationUtils

class EventListOwnedAdapter(private val itemPresenterBridge: EventListOwnedPresenterBridge) :
    BaseAdapter<EventListItemUiModel, EventListOwnedViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventListOwnedViewHolder {
        return EventListOwnedViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.event_list_item,
                parent,
                false
            ),
            itemPresenterBridge
        )
    }
}

class EventListOwnedViewHolder(
    itemView: View,
    private val presenterBridge: EventListOwnedPresenterBridge
) : BaseViewHolder<EventListItemUiModel>(itemView) {
    override fun bind(model: EventListItemUiModel) {
        val binding = EventListItemBinding.bind(itemView)

        binding.root.setOnClickListener {
            presenterBridge.onEventItemClick(model.id)
        }

        binding.eventListItemNameTextView.text = model.eventName
        binding.eventListItemDescriptionTextView.text = model.eventDesc

        binding.eventListItemDateTextView.text =
            DatePresentationUtils.formatDateRange(model.startDate, model.endDate)
        binding.eventListItemTimeTextView.text =
            DatePresentationUtils.formatTimeRange(model.startDate, model.endDate)

        binding.eventListItemLocationTextView.text = model.eventLocation

        if (model.attendees.size == 0) {
            binding.eventListItemNumAttendeesTextView.visibility = View.INVISIBLE
        } else if (model.attendees.size > 0) {
            binding.eventListItemNumAttendeesTextView.visibility = View.VISIBLE
            binding.eventListItemNumAttendeesTextView.text =
                PeoplePresentationUtils.getAttendeeSummary(
                    binding.root.context.resources,
                    model.attendees
                )
        }
    }
}