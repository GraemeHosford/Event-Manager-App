package graeme.hosford.event.manager.app.core.injection

import dagger.Binds
import dagger.Module
import graeme.hosford.event.manager.app.core.login.LoginPresenter
import graeme.hosford.event.manager.app.core.login.presentation.LoginPresenterImpl
import javax.inject.Singleton

@Module
interface PresenterBindingModule {

    @Binds
    @Singleton
    fun loginPresenter(impl: LoginPresenterImpl) : LoginPresenter

}