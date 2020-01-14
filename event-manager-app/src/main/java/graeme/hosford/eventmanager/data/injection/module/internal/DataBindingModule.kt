package graeme.hosford.eventmanager.data.injection.module.internal

import dagger.Binds
import dagger.Module
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

}