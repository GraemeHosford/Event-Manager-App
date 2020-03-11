package graeme.hosford.eventmanager.business.attendees.detail

import graeme.hosford.eventmanager.business.common.BaseInteractor
import graeme.hosford.eventmanager.data.event.detail.EventDetailFirebaseAccess
import graeme.hosford.eventmanager.data.login.CurrentUserNetworkAccess
import graeme.hosford.eventmanager.entity.event.Event
import javax.inject.Inject

class AttendeesDetailInteractorImpl @Inject constructor(
    private val eventDetailFirebaseAccessImpl: EventDetailFirebaseAccess,
    private val currentUserNetworkAccess: CurrentUserNetworkAccess
) : BaseInteractor<AttendeesDetailInteractor.AttendeesDetailCallback>(), AttendeesDetailInteractor {

    override fun onCreate() {
        super.onCreate()
        eventDetailFirebaseAccessImpl.registerCallback(EventDetailCallback())
    }

    override fun getEventAttendees(eventId: String) {
        eventDetailFirebaseAccessImpl.getEventDetails(
            currentUserNetworkAccess.getCurrentUser()!!.email!!,
            eventId
        )
    }

    private inner class EventDetailCallback : EventDetailFirebaseAccess.EventDetailCallback {
        override fun onEventRetrieved(event: Event) {

        }

        override fun onEventRetrievalFailed() {

        }
    }
}