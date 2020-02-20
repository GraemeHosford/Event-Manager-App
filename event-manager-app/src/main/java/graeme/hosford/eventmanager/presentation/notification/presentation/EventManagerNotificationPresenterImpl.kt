package graeme.hosford.eventmanager.presentation.notification.presentation

import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.business.notification.EventManagerNotificationInteractor
import graeme.hosford.eventmanager.presentation.common.presenter.BasePresenter
import graeme.hosford.eventmanager.presentation.notification.EventManagerNotificationPresenter
import graeme.hosford.eventmanager.presentation.notification.EventManagerNotificationView
import javax.inject.Inject

class EventManagerNotificationPresenterImpl @Inject constructor(
    private val interactor: EventManagerNotificationInteractor
) : BasePresenter<EventManagerNotificationView, EventManagerNotificationInteractor>(interactor),
    EventManagerNotificationPresenter {

    override fun onViewCreated(view: EventManagerNotificationView) {
        super.onViewCreated(view)
        interactor.registerCallback(NotificationInteractorCallback())
    }

    override fun saveUserToken(token: String) {
        interactor.saveUserMessagingToken(token)
    }

    private inner class NotificationInteractorCallback :
        EventManagerNotificationInteractor.NotificationInteractorCallback {
        override fun onMessagingTokenSaved() {
            /* Do nothing here for now */
        }

        override fun onMessagingTokenSaveFailed() {
            view?.showLongToast(R.string.notification_token_save_error_message)
        }
    }
}