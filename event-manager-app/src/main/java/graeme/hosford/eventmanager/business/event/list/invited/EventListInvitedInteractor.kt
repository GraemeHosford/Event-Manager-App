package graeme.hosford.eventmanager.business.event.list.invited

import graeme.hosford.eventmanager.business.event.list.common.BaseEventListInteractor

interface EventListInvitedInteractor : BaseEventListInteractor {
    fun getInvitedEvents()
}