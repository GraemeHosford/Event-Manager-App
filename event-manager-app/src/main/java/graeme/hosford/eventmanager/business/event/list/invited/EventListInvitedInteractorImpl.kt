package graeme.hosford.eventmanager.business.event.list.invited

import graeme.hosford.eventmanager.business.event.list.common.BaseEventListInteractorImpl
import graeme.hosford.eventmanager.data.event.list.EventListFirebaseAccess
import graeme.hosford.eventmanager.data.login.CurrentUserNetworkAccess
import javax.inject.Inject

class EventListInvitedInteractorImpl @Inject constructor(
    private val currentUserNetworkAccess: CurrentUserNetworkAccess,
    private val eventListFirebaseAccess: EventListFirebaseAccess
) : BaseEventListInteractorImpl(eventListFirebaseAccess, currentUserNetworkAccess),
    EventListInvitedInteractor {

    override fun getInvitedEvents() {
        eventListFirebaseAccess.getInvitedEvents(currentUserNetworkAccess.getCurrentUser()!!.email!!)
    }
}