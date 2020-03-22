package graeme.hosford.eventmanager.business.notification

import graeme.hosford.eventmanager.business.common.BaseInteractor
import graeme.hosford.eventmanager.business.common.Interactor
import graeme.hosford.eventmanager.business.event.list.invited.EventListInvitedInteractor
import graeme.hosford.eventmanager.data.login.CurrentUserNetworkAccess
import javax.inject.Inject

class EventManagerNotificationInteractorImpl @Inject constructor(
    private val userNetworkAccess: CurrentUserNetworkAccess,
    private val eventInvitedInteractor: EventListInvitedInteractor
) : BaseInteractor<EventManagerNotificationInteractor.NotificationInteractorCallback>(),
    EventManagerNotificationInteractor {

    override fun onCreate() {
        super.onCreate()
        userNetworkAccess.setUserInfoSavedListener(UserInfoSavedListener())
    }

    override fun registerManagedInteractors(): List<Interactor<*>> = listOf(eventInvitedInteractor)

    override fun updateEventInviteStatus(eventId: String, attending: Boolean) {
        eventInvitedInteractor.updateAttendingStatus(eventId, attending)
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