package graeme.hosford.eventmanager.business.injection.module.internal

import dagger.Binds
import dagger.Module
import graeme.hosford.eventmanager.business.attendees.AttendeesInteractor
import graeme.hosford.eventmanager.business.attendees.AttendeesInteractorImpl
import graeme.hosford.eventmanager.business.company.create.CreateCompanyInteractor
import graeme.hosford.eventmanager.business.company.create.CreateCompanyInteractorImpl
import graeme.hosford.eventmanager.business.company.detail.CompanyDetailInteractor
import graeme.hosford.eventmanager.business.company.detail.CompanyDetailInteractorImpl
import graeme.hosford.eventmanager.business.company.join.JoinCompanyInteractor
import graeme.hosford.eventmanager.business.company.join.JoinCompanyInteractorImpl
import graeme.hosford.eventmanager.business.event.create.CreateEventInteractor
import graeme.hosford.eventmanager.business.event.create.CreateEventInteractorImpl
import graeme.hosford.eventmanager.business.event.list.EventListInteractor
import graeme.hosford.eventmanager.business.event.list.EventListInteractorImpl
import graeme.hosford.eventmanager.business.login.LoginInteractor
import graeme.hosford.eventmanager.business.login.LoginInteractorImpl
import graeme.hosford.eventmanager.business.user.CurrentUserInteractor
import graeme.hosford.eventmanager.business.user.CurrentUserInteractorImpl
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

    @Binds
    @Singleton
    fun joinCompanyInteractor(impl: JoinCompanyInteractorImpl): JoinCompanyInteractor

    @Binds
    @Singleton
    fun eventListInteractor(impl: EventListInteractorImpl): EventListInteractor

    @Binds
    @Singleton
    fun currentUserInteractor(impl: CurrentUserInteractorImpl): CurrentUserInteractor

    @Binds
    @Singleton
    fun companyDetailInteractor(impl: CompanyDetailInteractorImpl): CompanyDetailInteractor

    @Binds
    @Singleton
    fun createEventInteractor(impl: CreateEventInteractorImpl): CreateEventInteractor

    @Binds
    @Singleton
    fun attendeesInteractor(impl: AttendeesInteractorImpl): AttendeesInteractor

    //endregion

}