package graeme.hosford.eventmanager.presentation.notification.service

import android.app.IntentService
import android.content.Intent
import graeme.hosford.eventmanager.EventManagerApplication
import graeme.hosford.eventmanager.presentation.notification.EventManagerNotificationPresenter
import javax.inject.Inject

class EventResponseService : IntentService("EventResponseService") {

    /* This should obviously be its own presenter to handle the event responses but very behind on
    * college work right now so need to just reuse this for the moment */
    @Inject
    lateinit var presenter: EventManagerNotificationPresenter

    override fun onCreate() {
        EventManagerApplication.appComponent.inject(this)
        super.onCreate()
    }

    override fun onHandleIntent(intent: Intent?) {
        if (intent == null) {
            return
        }

        if (intent.getStringExtra("Action") == "Accept") {
            presenter.onAcceptClick(intent.getStringExtra("EventID"))
        } else {
            presenter.onDeclineClick(intent.getStringExtra("EventID"))
        }
    }
}