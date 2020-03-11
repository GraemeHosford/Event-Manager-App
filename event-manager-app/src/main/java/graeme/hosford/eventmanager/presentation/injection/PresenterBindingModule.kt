package graeme.hosford.eventmanager.presentation.injection

import dagger.Binds
import dagger.Module
import graeme.hosford.eventmanager.presentation.attendees.choose.AttendeesPresenter
import graeme.hosford.eventmanager.presentation.attendees.choose.presentation.AttendeesPresenterImpl
import graeme.hosford.eventmanager.presentation.company.create.CreateCompanyPresenter
import graeme.hosford.eventmanager.presentation.company.create.presentation.CreateCompanyPresenterImpl
import graeme.hosford.eventmanager.presentation.company.detail.CompanyDetailPresenter
import graeme.hosford.eventmanager.presentation.company.detail.presentation.CompanyDetailPresenterImpl
import graeme.hosford.eventmanager.presentation.company.join.JoinCompanyPresenter
import graeme.hosford.eventmanager.presentation.company.join.presentation.JoinCompanyPresenterImpl
import graeme.hosford.eventmanager.presentation.event.create.CreateEventPresenter
import graeme.hosford.eventmanager.presentation.event.create.presentation.CreateEventPresenterImpl
import graeme.hosford.eventmanager.presentation.event.detail.EventDetailPresenter
import graeme.hosford.eventmanager.presentation.event.detail.presentation.EventDetailPresenterImpl
import graeme.hosford.eventmanager.presentation.event.list.attending.EventListAttendingPresenter
import graeme.hosford.eventmanager.presentation.event.list.attending.presentation.EventListAttendingPresenterImpl
import graeme.hosford.eventmanager.presentation.event.list.invited.EventListInvitedPresenter
import graeme.hosford.eventmanager.presentation.event.list.invited.presentation.EventListInvitedPresenterImpl
import graeme.hosford.eventmanager.presentation.login.LoginPresenter
import graeme.hosford.eventmanager.presentation.login.presentation.LoginPresenterImpl
import graeme.hosford.eventmanager.presentation.notification.EventManagerNotificationPresenter
import graeme.hosford.eventmanager.presentation.notification.presentation.EventManagerNotificationPresenterImpl
import graeme.hosford.eventmanager.presentation.profile.create.CreateProfilePresenter
import graeme.hosford.eventmanager.presentation.profile.create.presentation.CreateProfilePresenterImpl
import graeme.hosford.eventmanager.presentation.profile.detail.ProfileDetailPresenter
import graeme.hosford.eventmanager.presentation.profile.detail.presentation.ProfileDetailPresenterImpl
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
    fun eventListInvitedPresenter(impl: EventListInvitedPresenterImpl): EventListInvitedPresenter

    @Binds
    @Singleton
    fun eventListAttendingPresenter(impl: EventListAttendingPresenterImpl): EventListAttendingPresenter

    @Binds
    @Singleton
    fun companyDetailPresenter(impl: CompanyDetailPresenterImpl): CompanyDetailPresenter

    @Binds
    fun createEventPresenter(impl: CreateEventPresenterImpl): CreateEventPresenter

    @Binds
    @Singleton
    fun attendeesPresenter(impl: AttendeesPresenterImpl): AttendeesPresenter

    @Binds
    @Singleton
    fun eventDetailPresenter(impl: EventDetailPresenterImpl): EventDetailPresenter

    @Binds
    @Singleton
    fun eventManagerNotificationPresenter(impl: EventManagerNotificationPresenterImpl): EventManagerNotificationPresenter

    @Binds
    fun createProfilePresenter(impl: CreateProfilePresenterImpl): CreateProfilePresenter

    @Binds
    @Singleton
    fun profileDetailPresenter(impl: ProfileDetailPresenterImpl): ProfileDetailPresenter

}