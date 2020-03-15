package graeme.hosford.eventmanager.business.usereventdetail.detail

import graeme.hosford.eventmanager.business.common.BaseInteractor
import graeme.hosford.eventmanager.data.usereventdetail.UserEventDetailFirebaseAccess
import graeme.hosford.eventmanager.entity.usereventdetail.UserEventDetail
import javax.inject.Inject

class UserEventDetailListInteractorImpl @Inject constructor(
    private val userEventDetailFirebaseAccess: UserEventDetailFirebaseAccess
) : BaseInteractor<UserEventDetailListInteractor.UserEventDetailListCallback>(),
    UserEventDetailListInteractor {

    override fun onCreate() {
        super.onCreate()
        userEventDetailFirebaseAccess.retrievalCallback = UserEventDetailsRetrievakCallback()
    }

    override fun getUserEventDetails(userId: String) {
        userEventDetailFirebaseAccess.getUserEventDetails(userId)
    }

    private inner class UserEventDetailsRetrievakCallback: UserEventDetailFirebaseAccess.UserEventDetailsRetrievalCallback {
        override fun onDetailsRetrieved(entities: List<UserEventDetail>) {
            callback?.onDetailsRetrieved(entities)
        }

        override fun onRetrievalFailed() {
            callback?.onDetailRetrievalFailed()
        }
    }
}