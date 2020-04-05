package graeme.hosford.eventmanager.presentation.event.list.owned

import graeme.hosford.eventmanager.presentation.event.list.common.BaseEventListPresenter

interface EventListOwnedPresenter : BaseEventListPresenter {

    fun getOwnedEvents()

}