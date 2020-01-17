package graeme.hosford.eventmanager.presentation.injection

import dagger.Binds
import dagger.Module
import graeme.hosford.eventmanager.presentation.company.create.CreateCompanyPresenter
import graeme.hosford.eventmanager.presentation.company.create.presentation.CreateCompanyPresenterImpl
import graeme.hosford.eventmanager.presentation.company.join.JoinCompanyPresenter
import graeme.hosford.eventmanager.presentation.company.join.presentation.JoinCompanyPresenterImpl
import graeme.hosford.eventmanager.presentation.login.LoginPresenter
import graeme.hosford.eventmanager.presentation.login.presentation.LoginPresenterImpl
import javax.inject.Singleton

@Module
interface PresenterBindingModule {

    @Binds
    @Singleton
    fun loginPresenter(impl: LoginPresenterImpl): LoginPresenter

    @Binds
    @Singleton
    fun createJoinCompanyPresenter(impl: CreateJoinCompanyPresenterImpl): CreateJoinCompanyPresenter

    @Binds
    @Singleton
    fun joinCompanyPresenter(impl: JoinCompanyPresenterImpl): JoinCompanyPresenter

    @Binds
    @Singleton
    fun createCompanyPresenter(impl: CreateCompanyPresenterImpl): CreateCompanyPresenter

}