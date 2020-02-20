package graeme.hosford.eventmanager.business.notification

import graeme.hosford.eventmanager.business.common.Interactor

interface EventManagerNotificationInteractor :
    Interactor<EventManagerNotificationInteractor.NotificationInteractorCallback> {

    interface NotificationInteractorCallback {
        fun onMessagingTokenSaved()

        fun onMessagingTokenSaveFailed()
    }

    fun saveUserMessagingToken(token: String)

}