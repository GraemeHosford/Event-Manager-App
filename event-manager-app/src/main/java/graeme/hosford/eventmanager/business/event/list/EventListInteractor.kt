package graeme.hosford.eventmanager.business.event.list

import graeme.hosford.eventmanager.business.common.Interactor
import graeme.hosford.eventmanager.entity.event.Event

interface EventListInteractor : Interactor<EventListInteractor.EventListCallback> {

    interface EventListCallback {
        fun onEventsRetrieved(entites: List<Event>)

        fun onEventsRetrievalFailure()
    }

    fun getEvents()

}