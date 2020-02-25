package graeme.hosford.eventmanager.presentation.event.list

import graeme.hosford.eventmanager.presentation.common.presenter.Presenter

interface EventListPresenter : Presenter<EventListView> {

    fun onGoingResponseClick(eventId: String)

    fun onNotGoingResponseClick(eventId: String)

    fun onEventItemClick(eventId: String)

    fun onFabClick()

}