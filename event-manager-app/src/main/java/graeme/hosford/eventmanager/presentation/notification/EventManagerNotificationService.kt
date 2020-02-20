package graeme.hosford.eventmanager.presentation.notification

import graeme.hosford.eventmanager.EventManagerApplication
import graeme.hosford.eventmanager.presentation.common.view.firebase.BaseMessagingService
import javax.inject.Inject

class EventManagerNotificationService : BaseMessagingService(), EventManagerNotificationView {

    @Inject
    lateinit var presenter: EventManagerNotificationPresenter

    override fun onCreate() {
        EventManagerApplication.appComponent.inject(this)
        super.onCreate()
        presenter.onViewCreated(this)
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        presenter.saveUserToken(token)
    }
}