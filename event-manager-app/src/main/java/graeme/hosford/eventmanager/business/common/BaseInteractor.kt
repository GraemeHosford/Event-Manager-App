package graeme.hosford.eventmanager.business.common

import androidx.annotation.CallSuper

abstract class BaseInteractor<C> : Interactor<C> {

    var callback: C? = null
        private set

    @CallSuper
    override fun onCreate() {
        registerManagedInteractors().forEach {
            it.onCreate()
        }
    }

    override fun registerCallback(callback: C) {
        this.callback = callback
    }

    override fun registerManagedInteractors(): List<Interactor<*>> = emptyList()
}