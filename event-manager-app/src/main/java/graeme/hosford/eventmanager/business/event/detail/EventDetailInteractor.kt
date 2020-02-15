package graeme.hosford.eventmanager.business.event.detail

import graeme.hosford.eventmanager.business.common.Interactor
import graeme.hosford.eventmanager.entity.event.Event

interface EventDetailInteractor : Interactor<EventDetailInteractor.EventDetailCallback> {

    interface EventDetailCallback {
        fun onEventLoaded(event: Event)

        fun onLoadFailure()
    }

    fun retrieveEvent(eventId: String)

}