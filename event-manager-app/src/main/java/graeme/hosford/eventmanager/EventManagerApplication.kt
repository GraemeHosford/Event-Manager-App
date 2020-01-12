package graeme.hosford.eventmanager

import android.app.Application
import graeme.hosford.event.manager.business.EventManagerBusinessApplication
import graeme.hosford.event.manager.business.injection.BusinessComponent
import graeme.hosford.event.manager.data.bridge.DataBridgeInitializer
import graeme.hosford.eventmanager.injection.ApplicationComponent
import graeme.hosford.eventmanager.injection.DaggerApplicationComponent

/**
 * Projects [Application] concrete class.
 *
 * Don't use for lengthy unnecessary initializations, as anything executed here on the main thread will delay the
 * application startup and UI.
 */
class EventManagerApplication : EventManagerBusinessApplication() {

    companion object {

        lateinit var instance: EventManagerApplication
            private set

        val appComponent: ApplicationComponent
            get() = ApplicationComponent.INSTANCE
    }

    override fun onCreate() {
        instance = this
        super.onCreate()
    }

    override fun initializeAppComponent(businessComponent: BusinessComponent) {
        ApplicationComponent.INSTANCE = buildAppComponent(businessComponent)
    }

    override fun initializeDataComponent() {
        DataBridgeInitializer.initialize(this)
    }

    override fun initializeNetworkLayer() {
        DataBridgeInitializer.initializeNetworkLayer(this)
    }

    override fun initializeCacheLayer() {
        DataBridgeInitializer.initializeCacheLayer(this)
    }

    private fun buildAppComponent(businessComponent: BusinessComponent): ApplicationComponent {
        return DaggerApplicationComponent.factory().create(this, businessComponent)
    }

}