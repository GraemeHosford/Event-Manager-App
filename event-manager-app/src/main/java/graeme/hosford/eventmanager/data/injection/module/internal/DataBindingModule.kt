package graeme.hosford.eventmanager.data.injection.module.internal

import dagger.Binds
import dagger.Module
import graeme.hosford.eventmanager.data.company.CompanyFirebaseAccess
import graeme.hosford.eventmanager.data.company.CompanyFirebaseAccessImpl
import graeme.hosford.eventmanager.data.event.create.CreateEventFirebaseAccess
import graeme.hosford.eventmanager.data.event.create.CreateEventFirebaseAccessImpl
import graeme.hosford.eventmanager.data.event.detail.EventDetailFirebaseAccess
import graeme.hosford.eventmanager.data.event.detail.EventDetailFirebaseAccessImpl
import graeme.hosford.eventmanager.data.event.list.EventListFirebaseAccess
import graeme.hosford.eventmanager.data.event.list.EventListFirebaseAccessImpl
import graeme.hosford.eventmanager.data.login.CurrentUserNetworkAccess
import graeme.hosford.eventmanager.data.login.CurrentUserNetworkAccessImpl
import javax.inject.Singleton

/**
 * Dagger class bindings for data repository components.
 */
@Module
internal interface DataBindingModule {

    @Binds
    @Singleton
    fun currentUserAccess(impl: CurrentUserNetworkAccessImpl): CurrentUserNetworkAccess

    @Binds
    @Singleton
    fun companyFirebaseAccess(impl: CompanyFirebaseAccessImpl): CompanyFirebaseAccess

    @Binds
    @Singleton
    fun eventListFirebaseAccess(impl: EventListFirebaseAccessImpl): EventListFirebaseAccess

    @Binds
    @Singleton
    fun eventDetailFirebaseAccess(impl: EventDetailFirebaseAccessImpl): EventDetailFirebaseAccess

    @Binds
    @Singleton
    fun createEventFirebaseAccess(impl: CreateEventFirebaseAccessImpl): CreateEventFirebaseAccess

}