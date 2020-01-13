package graeme.hosford.event.manager.app.core.injection

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import graeme.hosford.event.manager.app.core.login.view.LoginActivity
import graeme.hosford.event.manager.business.injection.BusinessComponent
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        PresenterBindingModule::class
    ],
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
        @JvmStatic
        lateinit var INSTANCE: ApplicationComponent
    }

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance applicationContext: Context,
            businessComponent: BusinessComponent
        ): ApplicationComponent

    }

    fun inject(loginActivity: LoginActivity)

}