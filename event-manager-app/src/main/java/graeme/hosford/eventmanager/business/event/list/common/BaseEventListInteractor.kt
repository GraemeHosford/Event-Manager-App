package graeme.hosford.eventmanager.business.event.list.common

import graeme.hosford.eventmanager.business.common.Interactor
import graeme.hosford.eventmanager.entity.event.Event

interface BaseEventListInteractor : Interactor<BaseEventListInteractor.EventListCallback> {

    interface EventListCallback {
        fun onEventsRetrieved(entites: List<Event>)

        fun onEventsRetrievalFailure()
    }

    fun updateAttendingStatus(eventId: String, attending: Boolean)

    fun getEvents()

    fun getCurrentUserId(): String

}