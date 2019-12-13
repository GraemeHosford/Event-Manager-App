package graeme.hosford.event.manager.business.internal

import com.teamwork.android.samples.clean.entity.feature2.Entity2
import graeme.hosford.event.manager.business.Interactor

/**
 * This [Interactor] is only mean to be used within the `business` layer, so it can be declared as `internal`.
 */
internal interface InternalInteractor : Interactor<Entity2, Interactor.Callback<Entity2>> {

    fun initialize()

}