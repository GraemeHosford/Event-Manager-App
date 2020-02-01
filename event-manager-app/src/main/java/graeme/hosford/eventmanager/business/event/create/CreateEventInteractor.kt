package graeme.hosford.eventmanager.business.event.create

import graeme.hosford.eventmanager.business.common.Interactor

interface CreateEventInteractor : Interactor<CreateEventInteractor.CreateEventCallback> {

    interface CreateEventCallback

}