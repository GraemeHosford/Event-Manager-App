package graeme.hosford.eventmanager.data.event.list

import graeme.hosford.eventmanager.entity.event.Event

interface EventListFirebaseAccess {

    companion object {
        /* Called subcollection as this is within a Company document */
        const val EVENTS_SUBCOLLECTION = "Events"
    }

    interface EventDataListener {
        fun onEventRetrieveSuccess(entities: List<Event>)

        fun onEventRetrieveFailure()
    }

    fun setEventListener(listener: EventDataListener)

    fun getAllEvents(userEmail: String)

}