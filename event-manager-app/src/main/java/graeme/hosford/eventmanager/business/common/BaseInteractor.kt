package graeme.hosford.eventmanager.business.common

import androidx.annotation.CallSuper

abstract class BaseInteractor {

    @CallSuper
    open fun onCreate() {
    }

}