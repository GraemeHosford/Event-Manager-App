package graeme.hosford.eventmanager.business.event.list.attending

import graeme.hosford.eventmanager.business.event.list.common.BaseEventListInteractorImpl
import graeme.hosford.eventmanager.data.event.list.EventListFirebaseAccess
import graeme.hosford.eventmanager.data.login.CurrentUserNetworkAccess
import javax.inject.Inject

class EventListAttendingInteractorImpl @Inject constructor(
    private val currentUserNetworkAccess: CurrentUserNetworkAccess,
    private val eventListFirebaseAccess: EventListFirebaseAccess
) : BaseEventListInteractorImpl(eventListFirebaseAccess, currentUserNetworkAccess),
    EventListAttendingInteractor {

    override fun getAttendingEvents() {
        eventListFirebaseAccess.getAttendingEvents(currentUserNetworkAccess.getCurrentUser()!!.email!!)
    }
}