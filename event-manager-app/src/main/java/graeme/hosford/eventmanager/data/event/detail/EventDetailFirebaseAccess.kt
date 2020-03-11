package graeme.hosford.eventmanager.data.event.detail

import graeme.hosford.eventmanager.entity.event.Event

interface EventDetailFirebaseAccess {

    interface EventDetailCallback {
        fun onEventRetrieved(event: Event)

        fun onEventRetrievalFailed()
    }

    fun registerCallback(callback: EventDetailCallback)

    fun getEventDetails(companyId: String, eventId: String)

    fun getUserEventDetails(userId: String, eventId: String)

}