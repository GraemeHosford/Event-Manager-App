package graeme.hosford.eventmanager.business.event.list

import graeme.hosford.eventmanager.business.common.Interactor

interface EventListInteractor : Interactor<EventListInteractor.EventListCallback> {

    interface EventListCallback {

    }

}