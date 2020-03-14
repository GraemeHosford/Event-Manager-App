package graeme.hosford.eventmanager.business.usereventdetail

import graeme.hosford.eventmanager.business.common.Interactor

interface UserEventDetailInteractor :
    Interactor<UserEventDetailInteractor.UserEventDetailInteractorCallback> {

    interface UserEventDetailInteractorCallback

}