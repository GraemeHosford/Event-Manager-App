package graeme.hosford.event.manager.data.access.injection

import dagger.Binds
import dagger.Module
import graeme.hosford.event.manager.data.access.login.CurrentUserRepo
import graeme.hosford.event.manager.data.access.login.CurrentUserRepoImpl
import javax.inject.Singleton

@Module
interface DataAccessBindingModule {

    @Binds
    @Singleton
    fun currentUserRepo(impl: CurrentUserRepoImpl): CurrentUserRepo

}