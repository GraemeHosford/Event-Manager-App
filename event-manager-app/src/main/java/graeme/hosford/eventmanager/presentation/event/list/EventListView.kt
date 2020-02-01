package graeme.hosford.eventmanager.presentation.event.list

import graeme.hosford.eventmanager.presentation.common.view.recyclerview.RecyclerViewListView
import graeme.hosford.eventmanager.presentation.common.view.toast.ToastView
import graeme.hosford.eventmanager.presentation.event.list.model.EventListItemUiModel

interface EventListView : RecyclerViewListView<EventListItemUiModel>, ToastView {

    fun showEventDetail()

    fun startCreateNewEvent()

}