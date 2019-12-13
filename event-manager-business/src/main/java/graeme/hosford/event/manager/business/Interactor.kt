package graeme.hosford.event.manager.business

import androidx.annotation.MainThread
import graeme.hosford.event.manager.entity.BusinessEntity

/**
 * An interactor (or use case) is the bridge between the presentation and the data layer, to which it calls for data
 * loading and storage. It contains all application-specific business rules and logic and is completely unaware of the
 * presentation and data layer implementation details.
 *
 */
@MainThread
interface Interactor<T : BusinessEntity, C : Interactor.Callback<T>> {

    interface Callback<T : BusinessEntity> {
        fun onDataLoaded(data: T)
        fun onDataLoadError(exception: Exception)
    }

    fun registerCallback(callback: C)

    fun unregisterCallback()

}