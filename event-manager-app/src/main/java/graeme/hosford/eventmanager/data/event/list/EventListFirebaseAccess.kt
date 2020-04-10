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

    fun setAttendingStatus(eventId: String, userId: String, attending: Boolean)

    fun setEventListener(listener: EventDataListener)

    fun getAllEvents(userEmail: String)

    fun getAttendingEvents(userId: String)

    fun getInvitedEvents(userId: String)

    fun getOwnedEvents(userId: String)

    fun getDayEvents(userId: String, dayStart: Long)

}