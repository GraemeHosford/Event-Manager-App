package graeme.hosford.eventmanager.presentation.event.list.common

import graeme.hosford.eventmanager.presentation.common.presenter.Presenter

interface BaseEventListPresenter : Presenter<EventListView> {

    fun onEventItemClick(eventId: String)

    fun onFabClick()

    fun getCurrentUserId(): String

}