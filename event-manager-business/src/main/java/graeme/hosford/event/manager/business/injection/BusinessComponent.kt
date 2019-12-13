package graeme.hosford.event.manager.business.injection

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import graeme.hosford.event.manager.business.injection.module.internal.InteractorsBindingModule
import graeme.hosford.event.manager.data.access.DataAccessComponent
import java.util.concurrent.ExecutorService
import javax.inject.Named
import javax.inject.Singleton

interface BusinessComponent {

    companion object {
        const val GLOBAL_COMPUTATION_EXECUTOR = DataAccessComponent.GLOBAL_COMPUTATION_EXECUTOR
    }

    /*
     * Provision methods for public interactors that need to be exposed to the presentation layer.
     * Any other interactor implementation which is not declared here will only be accessible from within this layer.
     */


    //region exposed cross-layer dependencies

    @Named(GLOBAL_COMPUTATION_EXECUTOR)
    fun computationExecutor(): ExecutorService

    //endregion

}

@Singleton
@Component(
    modules = [
        InteractorsBindingModule::class
    ],
    dependencies = [
        DataAccessComponent::class
    ]
)
internal interface InternalBusinessComponent : BusinessComponent {

    companion object {
        /**
         * The singleton instance for [InternalBusinessComponent].
         * This is initialised by the `business` layer itself and primarily used to inject internal dependencies.
         * The instance can be replaced with a mock for testing when necessary.
         */
        @Volatile
        @JvmStatic
        lateinit var INSTANCE: InternalBusinessComponent
    }

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance applicationContext: Context,
            dataAccessComponent: DataAccessComponent
        ): InternalBusinessComponent
    }


    //region `data` layer injectable classes

    fun inject(businessLayerInitializer: BusinessLayerInitializer)

    //endregion

}