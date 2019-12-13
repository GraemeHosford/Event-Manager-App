package graeme.hosford.event.manager.data.injection

import android.content.Context
import graeme.hosford.event.manager.data.access.DataAccessComponent
import javax.annotation.concurrent.ThreadSafe
import javax.inject.Singleton

/**
 * Initializer class for the `data` layer.
 *
 * It takes care of triggering the initialization of the [DataComponent] from [initialize], and of all the
 * other layer initialization code dependent on the component in [initializeNetworkLayer] and [initializeCacheLayer].
 */
@Singleton
@ThreadSafe
class DataLayerInitializer {

    fun initialize(appContext: Context) {
        initializeDataComponent(appContext)

        DataComponent.INSTANCE.inject(this)
    }

    private fun initializeDataComponent(appContext: Context): DataComponent {
        val dataComponent = DaggerDataComponent.factory().create(appContext)
        DataComponent.INSTANCE = dataComponent
        DataAccessComponent.INSTANCE = dataComponent
        return dataComponent
    }

    fun initializeNetworkLayer(appContext: Context) {}

    fun initializeCacheLayer(appContext: Context) {}

}