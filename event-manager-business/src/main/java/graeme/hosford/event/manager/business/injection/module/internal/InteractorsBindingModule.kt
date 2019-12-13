package graeme.hosford.event.manager.business.injection.module.internal

import dagger.Binds
import dagger.Module
import graeme.hosford.event.manager.business.injection.BusinessComponent
import graeme.hosford.event.manager.business.internal.InternalInteractor
import graeme.hosford.event.manager.business.internal.InternalInteractorImpl
import javax.inject.Singleton

/**
 * This Dagger [Module] contains bindings and provider methods for interactors and other related objects which are
 * initialized within the internal business component.
 *
 * To expose the interactors to the app layer (i.e. most interactors will be needed from presenters), declare their
 * interfaces in [BusinessComponent].
 */
@Module
internal interface InteractorsBindingModule {

    //region interactor binders

    @Binds
    @Singleton
    fun internalInteractor(impl: InternalInteractorImpl): InternalInteractor

    //endregion

}