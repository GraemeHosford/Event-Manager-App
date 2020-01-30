package graeme.hosford.eventmanager.business.common

import androidx.annotation.MainThread

/**
 * An interactor (or use case) is the bridge between the presentation and the data layer, to which it calls for data
 * loading and storage. It contains all application-specific business rules and logic and is completely unaware of the
 * presentation and data layer implementation details.
 *
 */
@MainThread
interface Interactor<C> {

    fun onCreate()

    fun registerCallback(callback: C)

    fun registerManagedInteractors(): List<Interactor<*>>

}