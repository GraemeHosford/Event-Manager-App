package graeme.hosford.eventmanager.business.usereventdetail.detail

import graeme.hosford.eventmanager.business.common.Interactor
import graeme.hosford.eventmanager.entity.usereventdetail.UserEventDetail

interface UserEventDetailListInteractor :
    Interactor<UserEventDetailListInteractor.UserEventDetailListCallback> {

    interface UserEventDetailListCallback {
        fun onDetailsRetrieved(entities: List<UserEventDetail>)

        fun onDetailRetrievalFailed()
    }

    fun getUserEventDetails(userId: String)

}