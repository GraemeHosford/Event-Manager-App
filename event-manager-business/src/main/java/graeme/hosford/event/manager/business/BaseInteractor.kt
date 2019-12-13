package graeme.hosford.event.manager.business

import graeme.hosford.event.manager.entity.BusinessEntity

abstract class BaseInteractor<T : BusinessEntity, C : Interactor.Callback<T>> : Interactor<T, C> {

    var callback: C? = null

    override fun registerCallback(callback: C) {
        this.callback = callback
    }

    override fun unregisterCallback() {
        this.callback = null
    }

}