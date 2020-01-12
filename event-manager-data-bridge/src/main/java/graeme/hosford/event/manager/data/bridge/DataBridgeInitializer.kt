package graeme.hosford.event.manager.data.bridge

import android.content.Context
import graeme.hosford.event.manager.data.injection.DataLayerInitializer
import javax.annotation.concurrent.ThreadSafe
import javax.inject.Singleton

@Singleton
@ThreadSafe
object DataBridgeInitializer {

    private val dataInitializer: DataLayerInitializer by lazy { DataLayerInitializer() }

    fun initialize(appContext: Context) {
        dataInitializer.initialize(appContext)
    }

    fun initializeNetworkLayer(appContext: Context) {
        dataInitializer.initializeNetworkLayer(appContext)
    }

    fun initializeCacheLayer(appContext: Context) {
        dataInitializer.initializeCacheLayer(appContext)
    }

}