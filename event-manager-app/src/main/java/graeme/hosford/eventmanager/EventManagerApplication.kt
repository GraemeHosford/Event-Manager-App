package graeme.hosford.eventmanager

import android.app.Application
import graeme.hosford.eventmanager.presentation.injection.ApplicationComponent
import graeme.hosford.eventmanager.presentation.injection.DaggerApplicationComponent

/**
 * Projects [Application] concrete class.
 *
 * Don't use for lengthy unnecessary initializations, as anything executed here on the main thread will delay the
 * application startup and UI.
 */
class EventManagerApplication : Application() {

    companion object {

        lateinit var instance: EventManagerApplication
            private set

        lateinit var appComponent: ApplicationComponent
            private set
    }

    override fun onCreate() {
        instance = this
        super.onCreate()
        appComponent = DaggerApplicationComponent.create()
    }

}