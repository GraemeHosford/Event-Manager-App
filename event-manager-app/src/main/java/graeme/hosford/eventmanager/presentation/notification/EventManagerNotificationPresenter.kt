package graeme.hosford.eventmanager.presentation.notification

import graeme.hosford.eventmanager.presentation.common.presenter.Presenter

interface EventManagerNotificationPresenter : Presenter<EventManagerNotificationView> {
    fun saveUserToken(token: String)
}