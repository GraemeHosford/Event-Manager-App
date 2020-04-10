package graeme.hosford.eventmanager.presentation.event.calendar.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import graeme.hosford.eventmanager.databinding.EventListItemBinding
import graeme.hosford.eventmanager.presentation.common.view.recyclerview.BaseAdapter
import graeme.hosford.eventmanager.presentation.common.view.recyclerview.BaseViewHolder
import graeme.hosford.eventmanager.presentation.event.list.common.model.EventListItemUiModel
import graeme.hosford.eventmanager.presentation.utils.DatePresentationUtils
import graeme.hosford.eventmanager.presentation.utils.PeoplePresentationUtils

class EventDayListAdapter(private val presenterBridge: EventDayListPresenterBridge) :
    BaseAdapter<EventListItemUiModel, EventDayListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventDayListViewHolder {
        return EventDayListViewHolder(
            EventListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            presenterBridge
        )
    }
}

class EventDayListViewHolder(
    private val binding: EventListItemBinding,
    private val presenterBridge: EventDayListPresenterBridge
) : BaseViewHolder<EventListItemUiModel>(binding.root) {

    override fun bind(model: EventListItemUiModel) {
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