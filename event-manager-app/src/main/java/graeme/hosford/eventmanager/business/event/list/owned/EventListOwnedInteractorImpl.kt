package graeme.hosford.eventmanager.business.event.list.owned

import graeme.hosford.eventmanager.business.event.list.common.BaseEventListInteractorImpl
import graeme.hosford.eventmanager.data.event.list.EventListFirebaseAccess
import graeme.hosford.eventmanager.data.login.CurrentUserNetworkAccess
import javax.inject.Inject

class EventListOwnedInteractorImpl @Inject constructor(
    private val currentUserNetworkAccess: CurrentUserNetworkAccess,
    private val eventListFirebaseAccess: EventListFirebaseAccess
) : BaseEventListInteractorImpl(eventListFirebaseAccess, currentUserNetworkAccess),
    EventListOwnedInteractor {

    override fun getOwnedEvents() {
        eventListFirebaseAccess.getOwnedEvents(currentUserNetworkAccess.getCurrentUser()!!.email!!)
    }
}