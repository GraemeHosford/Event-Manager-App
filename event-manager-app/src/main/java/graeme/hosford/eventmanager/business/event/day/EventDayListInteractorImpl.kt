package graeme.hosford.eventmanager.business.event.day

import graeme.hosford.eventmanager.business.event.list.common.BaseEventListInteractorImpl
import graeme.hosford.eventmanager.data.event.list.EventListFirebaseAccess
import graeme.hosford.eventmanager.data.login.CurrentUserNetworkAccess
import javax.inject.Inject

class EventDayListInteractorImpl @Inject constructor(
    private val currentUserNetworkAccess: CurrentUserNetworkAccess,
    private val eventListFirebaseAccess: EventListFirebaseAccess
) : BaseEventListInteractorImpl(eventListFirebaseAccess, currentUserNetworkAccess),
    EventDayListInteractor {

    override fun getEventsOnDay(dayStart: Long) {
        eventListFirebaseAccess.getDayEvents(
            currentUserNetworkAccess.getCurrentUser()!!.email!!,
            dayStart
        )
    }
}