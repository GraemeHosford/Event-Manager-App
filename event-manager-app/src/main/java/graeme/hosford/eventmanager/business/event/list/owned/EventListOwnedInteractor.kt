package graeme.hosford.eventmanager.business.event.list.owned

import graeme.hosford.eventmanager.business.event.list.common.BaseEventListInteractor

interface EventListOwnedInteractor : BaseEventListInteractor {
    fun getOwnedEvents()
}