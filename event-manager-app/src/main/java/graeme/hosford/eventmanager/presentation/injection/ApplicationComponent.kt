package graeme.hosford.eventmanager.presentation.injection

import dagger.Component
import graeme.hosford.eventmanager.business.injection.module.internal.InteractorsBindingModule
import graeme.hosford.eventmanager.data.injection.module.internal.DataRepoBindingModule
import graeme.hosford.eventmanager.data.injection.module.internal.NetworkModule
import graeme.hosford.eventmanager.data.injection.module.internal.ThreadingModule
import graeme.hosford.eventmanager.presentation.login.view.LoginActivity
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        PresenterBindingModule::class,
        InteractorsBindingModule::class,
        DataRepoBindingModule::class,
        NetworkModule::class,
        ThreadingModule::class
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

    fun inject(loginActivity: LoginActivity)

}