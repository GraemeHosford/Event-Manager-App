package graeme.hosford.eventmanager.presentation.event.list.common.view.adapter

interface BaseEventListItemPresenterBridge {

    fun onEventListItemClick(eventId: String)

    fun getCurrentUserId(): String

}