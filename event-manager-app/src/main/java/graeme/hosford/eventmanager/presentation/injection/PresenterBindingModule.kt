package graeme.hosford.eventmanager.presentation.injection

import dagger.Binds
import dagger.Module
import graeme.hosford.eventmanager.presentation.company.create.CreateCompanyPresenter
import graeme.hosford.eventmanager.presentation.company.create.presentation.CreateCompanyPresenterImpl
import graeme.hosford.eventmanager.presentation.company.detail.CompanyDetailPresenter
import graeme.hosford.eventmanager.presentation.company.detail.presentation.CompanyDetailPresenterImpl
import graeme.hosford.eventmanager.presentation.company.join.JoinCompanyPresenter
import graeme.hosford.eventmanager.presentation.company.join.presentation.JoinCompanyPresenterImpl
import graeme.hosford.eventmanager.presentation.event.list.EventListPresenter
import graeme.hosford.eventmanager.presentation.event.list.presentation.EventListPresenterImpl
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
    fun joinCompanyPresenter(impl: JoinCompanyPresenterImpl): JoinCompanyPresenter

    @Binds
    @Singleton
    fun createCompanyPresenter(impl: CreateCompanyPresenterImpl): CreateCompanyPresenter

    @Binds
    @Singleton
    fun eventListPresenter(impl: EventListPresenterImpl): EventListPresenter

    @Binds
    @Singleton
    fun companyDetailPresenter(impl: CompanyDetailPresenterImpl): CompanyDetailPresenter

}