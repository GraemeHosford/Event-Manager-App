package graeme.hosford.eventmanager.presentation.notification.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.RemoteMessage
import graeme.hosford.eventmanager.EventManagerApplication
import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.presentation.CoreIntents
import graeme.hosford.eventmanager.presentation.common.view.firebase.BaseMessagingService
import graeme.hosford.eventmanager.presentation.notification.EventManagerNotificationPresenter
import graeme.hosford.eventmanager.presentation.notification.EventManagerNotificationView
import javax.inject.Inject

class EventManagerNotificationService : BaseMessagingService(),
    EventManagerNotificationView {

    companion object {
        const val CHANNEL_ID = "Event Manager Channel"
    }

    @Inject
    lateinit var presenter: EventManagerNotificationPresenter

    override fun onCreate() {
        EventManagerApplication.appComponent.inject(this)
        super.onCreate()
        presenter.onViewCreated(this)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        message.notification?.let {
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val pendingIntent =
                PendingIntent.getActivity(
                    this,
                    0,
                    CoreIntents.startAppIntent(this),
                    PendingIntent.FLAG_UPDATE_CURRENT
                )

            val notBuilder = NotificationCompat.Builder(
                this,
                CHANNEL_ID
            )
                .setContentTitle(it.title)
                .setContentText(it.body)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_calendar)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    CHANNEL_ID,
                    "Event Manager",
                    NotificationManager.IMPORTANCE_DEFAULT
                )

                notificationManager.createNotificationChannel(channel)
            }

            with(NotificationManagerCompat.from(this)) {
                notify(1, notBuilder.build())
            }
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        presenter.saveUserToken(token)
    }
}