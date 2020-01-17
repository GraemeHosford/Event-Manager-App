package graeme.hosford.eventmanager.business.injection.module.internal

import dagger.Binds
import dagger.Module
import graeme.hosford.eventmanager.business.company.create.CreateCompanyInteractor
import graeme.hosford.eventmanager.business.company.create.CreateCompanyInteractorImpl
import graeme.hosford.eventmanager.business.login.LoginInteractor
import graeme.hosford.eventmanager.business.login.LoginInteractorImpl
import javax.inject.Singleton

@Module
internal interface InteractorsBindingModule {

    //region interactor binders

    @Binds
    @Singleton
    fun loginInteractor(impl: LoginInteractorImpl): LoginInteractor

    @Binds
    @Singleton
    fun createCompanyInteractor(impl: CreateCompanyInteractorImpl): CreateCompanyInteractor

    //endregion

}