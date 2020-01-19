package graeme.hosford.eventmanager.business.common

import androidx.annotation.CallSuper

abstract class BaseInteractor<C> : Interactor<C> {

    var callback: C? = null
        private set

    @CallSuper
    override fun onCreate() {
    }

    override fun registerCallback(callback: C) {
        this.callback = callback
    }
}