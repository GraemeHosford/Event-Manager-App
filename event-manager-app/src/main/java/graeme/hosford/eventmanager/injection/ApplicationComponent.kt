package graeme.hosford.eventmanager.injection

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import graeme.hosford.event.manager.business.injection.BusinessComponent
import javax.inject.Singleton

@Singleton
@Component(
    modules = [],
    dependencies = [
        BusinessComponent::class
    ]
)
interface ApplicationComponent {

    companion object {
        /**
         * The singleton instance for [ApplicationComponent].
         * This is initialised by the `presentation` layer itself and primarily used to inject dependencies.
         * The instance can be replaced with a mock for testing when necessary.
         */
        @Volatile
        lateinit var INSTANCE: ApplicationComponent
    }

    interface Factory {
        fun create(
            @BindsInstance applicationContext: Context,
            businessComponent: BusinessComponent
        ): ApplicationComponent

    }

}