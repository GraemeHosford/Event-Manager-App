package graeme.hosford.eventmanager.business.notification

import graeme.hosford.eventmanager.business.common.BaseInteractor
import graeme.hosford.eventmanager.data.login.CurrentUserNetworkAccess
import javax.inject.Inject

class EventManagerNotificationInteractorImpl @Inject constructor(
    private val userNetworkAccess: CurrentUserNetworkAccess
) : BaseInteractor<EventManagerNotificationInteractor.NotificationInteractorCallback>(),
    EventManagerNotificationInteractor {

    override fun onCreate() {
        super.onCreate()
        userNetworkAccess.setUserInfoSavedListener(UserInfoSavedListener())
    }

    override fun saveUserMessagingToken(token: String) {
        userNetworkAccess.saveUserInfo(
            userNetworkAccess.getCurrentUser()!!.email!!,
            hashMapOf(
                "messaging_token" to token
            )
        )
    }

    private inner class UserInfoSavedListener : CurrentUserNetworkAccess.UserInfoSavedCallback {
        override fun onUserInfoSavedSuccess() {
            callback?.onMessagingTokenSaved()
        }

        override fun onUserInfoSavedFailure() {
            callback?.onMessagingTokenSaveFailed()
        }
    }
}