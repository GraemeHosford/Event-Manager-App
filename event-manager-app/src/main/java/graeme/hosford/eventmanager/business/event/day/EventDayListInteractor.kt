package graeme.hosford.eventmanager.business.event.day

import graeme.hosford.eventmanager.business.event.list.common.BaseEventListInteractor

interface EventDayListInteractor : BaseEventListInteractor {
    fun getEventsOnDay(dayStart: Long)
}