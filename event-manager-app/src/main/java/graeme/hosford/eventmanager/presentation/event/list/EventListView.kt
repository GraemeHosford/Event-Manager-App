package graeme.hosford.eventmanager.presentation.event.list

import graeme.hosford.eventmanager.presentation.common.view.recyclerview.RecyclerViewListView
import graeme.hosford.eventmanager.presentation.common.view.toast.ToastView
import graeme.hosford.eventmanager.presentation.event.list.model.EventListItemUiModel

interface EventListView : RecyclerViewListView<EventListItemUiModel>, ToastView {

    companion object {
        const val ARG_EVENT_ID = "ARG_EVENT_ID"
    }

    fun showEventDetail(eventId: String)

    fun startCreateNewEvent()

}