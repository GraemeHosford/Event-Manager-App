package graeme.hosford.eventmanager.presentation.injection

import dagger.Component
import graeme.hosford.eventmanager.business.injection.module.internal.InteractorsBindingModule
import graeme.hosford.eventmanager.data.injection.module.internal.DataBindingModule
import graeme.hosford.eventmanager.data.injection.module.internal.NetworkModule
import graeme.hosford.eventmanager.data.injection.module.internal.ThreadingModule
import graeme.hosford.eventmanager.presentation.company.create.view.CreateCompanyActivity
import graeme.hosford.eventmanager.presentation.company.create_join_screen.view.CreateJoinCompanyActivity
import graeme.hosford.eventmanager.presentation.company.detail.view.CompanyDetailFragment
import graeme.hosford.eventmanager.presentation.company.join.view.JoinCompanyActivity
import graeme.hosford.eventmanager.presentation.event.create.view.CreateEventFragment
import graeme.hosford.eventmanager.presentation.event.list.view.EventListFragment
import graeme.hosford.eventmanager.presentation.login.view.LoginActivity
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        PresenterBindingModule::class,
        InteractorsBindingModule::class,
        DataBindingModule::class,
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

    fun inject(createJoinCompanyActivity: CreateJoinCompanyActivity)

    fun inject(joinCompanyActivity: JoinCompanyActivity)

    fun inject(createCompanyActivity: CreateCompanyActivity)

    fun inject(eventListFragment: EventListFragment)

    fun inject(companyDetailFragment: CompanyDetailFragment)

    fun inject(createEventFragment: CreateEventFragment)

}