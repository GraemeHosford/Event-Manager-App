package graeme.hosford.eventmanager.business.usereventdetail

import graeme.hosford.eventmanager.business.common.BaseInteractor
import graeme.hosford.eventmanager.data.usereventdetail.UserEventDetailFirebaseAccess
import javax.inject.Inject

class UserEventDetailInteractorImpl @Inject constructor(
    private val userEventDetailFirebaseAccess: UserEventDetailFirebaseAccess
) : BaseInteractor<UserEventDetailInteractor.UserEventDetailInteractorCallback>(),
    UserEventDetailInteractor {

    override fun onCreate() {
        super.onCreate()
        userEventDetailFirebaseAccess.callback = UserEventDetailsCallback()
    }

    override fun saveEventDetail(userId: String, eventId: String, subject: String, detail: String) {
        userEventDetailFirebaseAccess.saveEventDetails(userId, eventId, subject, detail)
    }

    private inner class UserEventDetailsCallback :
        UserEventDetailFirebaseAccess.UserEventDetailsFirebaseAccessCallback {

        override fun onDetailsSaved() {
            callback?.onDetailsSaved()
        }

        override fun onDetailsSaveFailed() {
            callback?.onDetailsSaveFailed()
        }
    }
}