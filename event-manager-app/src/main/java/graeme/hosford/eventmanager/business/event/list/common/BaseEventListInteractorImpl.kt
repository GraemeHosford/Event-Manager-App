package graeme.hosford.eventmanager.business.event.list.common

import graeme.hosford.eventmanager.business.common.BaseInteractor
import graeme.hosford.eventmanager.data.event.list.EventListFirebaseAccess
import graeme.hosford.eventmanager.data.login.CurrentUserNetworkAccess
import graeme.hosford.eventmanager.entity.event.Event

abstract class BaseEventListInteractorImpl constructor(
    private val eventListFirebaseAccess: EventListFirebaseAccess,
    private val currentUserNetworkAccess: CurrentUserNetworkAccess
) : BaseInteractor<BaseEventListInteractor.EventListCallback>(),
    BaseEventListInteractor {

    override fun onCreate() {
        super.onCreate()
        eventListFirebaseAccess.setEventListener(EventListFirebaseListener())
    }

    override fun getEvents() {
        eventListFirebaseAccess.getAllEvents(currentUserNetworkAccess.getCurrentUser()?.email!!)
    }

    override fun updateAttendingStatus(eventId: String, attending: Boolean) {
        eventListFirebaseAccess.setAttendingStatus(
            eventId,
            getCurrentUserId(),
            attending
        )
    }

    override fun getCurrentUserId(): String {
        return currentUserNetworkAccess.getCurrentUser()!!.email!!
    }

    private inner class EventListFirebaseListener : EventListFirebaseAccess.EventDataListener {
        override fun onEventRetrieveSuccess(entities: List<Event>) {
            callback?.onEventsRetrieved(entities)
        }

        override fun onEventRetrieveFailure() {
            callback?.onEventsRetrievalFailure()
        }
    }
}