package graeme.hosford.eventmanager.presentation.event.list

import graeme.hosford.eventmanager.presentation.common.presenter.Presenter

interface EventListPresenter : Presenter<EventListView> {

    fun onEventItemClick()

    fun onFabClick()

}