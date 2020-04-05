package graeme.hosford.eventmanager.business.injection.module.internal

import dagger.Binds
import dagger.Module
import graeme.hosford.eventmanager.business.attendees.choose.ChooseAttendeesInteractor
import graeme.hosford.eventmanager.business.attendees.choose.ChooseAttendeesInteractorImpl
import graeme.hosford.eventmanager.business.attendees.detail.AttendeesDetailInteractor
import graeme.hosford.eventmanager.business.attendees.detail.AttendeesDetailInteractorImpl
import graeme.hosford.eventmanager.business.company.create.CreateCompanyInteractor
import graeme.hosford.eventmanager.business.company.create.CreateCompanyInteractorImpl
import graeme.hosford.eventmanager.business.company.detail.CompanyDetailInteractor
import graeme.hosford.eventmanager.business.company.detail.CompanyDetailInteractorImpl
import graeme.hosford.eventmanager.business.company.join.JoinCompanyInteractor
import graeme.hosford.eventmanager.business.company.join.JoinCompanyInteractorImpl
import graeme.hosford.eventmanager.business.event.create.CreateEventInteractor
import graeme.hosford.eventmanager.business.event.create.CreateEventInteractorImpl
import graeme.hosford.eventmanager.business.event.detail.EventDetailInteractor
import graeme.hosford.eventmanager.business.event.detail.EventDetailInteractorImpl
import graeme.hosford.eventmanager.business.event.list.attending.EventListAttendingInteractor
import graeme.hosford.eventmanager.business.event.list.attending.EventListAttendingInteractorImpl
import graeme.hosford.eventmanager.business.event.list.invited.EventListInvitedInteractor
import graeme.hosford.eventmanager.business.event.list.invited.EventListInvitedInteractorImpl
import graeme.hosford.eventmanager.business.event.list.owned.EventListOwnedInteractor
import graeme.hosford.eventmanager.business.event.list.owned.EventListOwnedInteractorImpl
import graeme.hosford.eventmanager.business.feedback.FeedbackInteractor
import graeme.hosford.eventmanager.business.feedback.FeedbackInteractorImpl
import graeme.hosford.eventmanager.business.login.LoginInteractor
import graeme.hosford.eventmanager.business.login.LoginInteractorImpl
import graeme.hosford.eventmanager.business.notification.EventManagerNotificationInteractor
import graeme.hosford.eventmanager.business.notification.EventManagerNotificationInteractorImpl
import graeme.hosford.eventmanager.business.profile.create.CreateProfileInteractor
import graeme.hosford.eventmanager.business.profile.create.CreateProfileInteractorImpl
import graeme.hosford.eventmanager.business.profile.detail.ProfileDetailInteractor
import graeme.hosford.eventmanager.business.profile.detail.ProfileDetailInteractorImpl
import graeme.hosford.eventmanager.business.user.CurrentUserInteractor
import graeme.hosford.eventmanager.business.user.CurrentUserInteractorImpl
import graeme.hosford.eventmanager.business.usereventdetail.create.UserEventDetailInteractor
import graeme.hosford.eventmanager.business.usereventdetail.create.UserEventDetailInteractorImpl
import graeme.hosford.eventmanager.business.usereventdetail.detail.UserEventDetailListInteractor
import graeme.hosford.eventmanager.business.usereventdetail.detail.UserEventDetailListInteractorImpl
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
    fun eventListAttendingInteractor(impl: EventListAttendingInteractorImpl): EventListAttendingInteractor

    @Binds
    @Singleton
    fun eventListInvitedInteractor(impl: EventListInvitedInteractorImpl): EventListInvitedInteractor

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
    fun attendeesInteractor(impl: ChooseAttendeesInteractorImpl): ChooseAttendeesInteractor

    @Binds
    @Singleton
    fun attendeesDetailAInteractor(impl: AttendeesDetailInteractorImpl): AttendeesDetailInteractor

    @Binds
    @Singleton
    fun eventDetailInteractor(impl: EventDetailInteractorImpl): EventDetailInteractor

    @Binds
    @Singleton
    fun eventManagerNotificationInteractor(impl: EventManagerNotificationInteractorImpl): EventManagerNotificationInteractor

    @Binds
    @Singleton
    fun createProfileInteractor(impl: CreateProfileInteractorImpl): CreateProfileInteractor

    @Binds
    @Singleton
    fun profileDetailInteractor(impl: ProfileDetailInteractorImpl): ProfileDetailInteractor

    @Binds
    @Singleton
    fun userEventDetailInteractor(impl: UserEventDetailInteractorImpl): UserEventDetailInteractor

    @Binds
    @Singleton
    fun userEventDetailListInteractor(impl: UserEventDetailListInteractorImpl): UserEventDetailListInteractor

    @Binds
    @Singleton
    fun feedbackInteractor(impl: FeedbackInteractorImpl): FeedbackInteractor

    @Binds
    @Singleton
    fun eventListOwnedInteractor(impl: EventListOwnedInteractorImpl): EventListOwnedInteractor

    //endregion

}