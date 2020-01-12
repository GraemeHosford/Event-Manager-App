@file:Suppress("UNUSED_PARAMETER")

package graeme.hosford.event.manager.business.injection

import android.content.Context
import graeme.hosford.event.manager.business.internal.InternalInteractor
import graeme.hosford.event.manager.data.access.DataAccessComponent
import graeme.hosford.event.manager.data.access.feature1.Entity1Repo
import javax.inject.Inject

/**
 * Initializer class for the `business` layer.
 *
 * It takes care of triggering the initialization of the [InternalBusinessComponent] from [initialize], and of all the
 * other layer initialization code dependent on the component in [initializeBusinessLayer].
 */
internal class BusinessLayerInitializer {

    @Inject
    lateinit var businessInternalDependency: InternalInteractor

    fun initialize(appContext: Context) {
        val dataAccessComponent = DataAccessComponent.INSTANCE
        val businessComponent = DaggerInternalBusinessComponent.factory()
            .create(appContext, dataAccessComponent)
        InternalBusinessComponent.INSTANCE = businessComponent
    }

    fun initializeBusinessLayer(appContext: Context) {
        InternalBusinessComponent.INSTANCE.inject(this)

        businessInternalDependency.initialize()
    }

}