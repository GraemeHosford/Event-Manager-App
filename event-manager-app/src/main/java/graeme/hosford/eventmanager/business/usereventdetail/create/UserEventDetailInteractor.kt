package graeme.hosford.eventmanager.business.usereventdetail.create

import graeme.hosford.eventmanager.business.common.Interactor

interface UserEventDetailInteractor :
    Interactor<UserEventDetailInteractor.UserEventDetailInteractorCallback> {

    interface UserEventDetailInteractorCallback {
        fun onDetailsSaved()

        fun onDetailsSaveFailed()
    }

    fun saveEventDetail(
        userId: String,
        eventId: String,
        eventName: String,
        subject: String,
        detail: String
    )

}