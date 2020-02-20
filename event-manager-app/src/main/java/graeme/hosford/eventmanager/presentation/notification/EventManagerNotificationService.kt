package graeme.hosford.eventmanager.presentation.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.RemoteMessage
import graeme.hosford.eventmanager.EventManagerApplication
import graeme.hosford.eventmanager.presentation.common.view.firebase.BaseMessagingService
import javax.inject.Inject

class EventManagerNotificationService : BaseMessagingService(), EventManagerNotificationView {

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

            val notBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle(it.title)
                .setContentText(it.body)
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