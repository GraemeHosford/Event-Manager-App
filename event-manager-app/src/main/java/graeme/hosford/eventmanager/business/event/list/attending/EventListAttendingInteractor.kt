package graeme.hosford.eventmanager.business.event.list.attending

import graeme.hosford.eventmanager.business.event.list.common.BaseEventListInteractor

interface EventListAttendingInteractor : BaseEventListInteractor {
    fun getAttendingEvents()
}