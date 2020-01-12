package graeme.hosford.event.manager.business.internal

import graeme.hosford.event.manager.entity.feature2.Entity2
import graeme.hosford.event.manager.business.BaseInteractor
import graeme.hosford.event.manager.business.Interactor
import graeme.hosford.event.manager.data.access.feature1.Entity1Repo
import graeme.hosford.event.manager.data.access.feature2.Entity2Repo
import javax.inject.Inject

internal class InternalInteractorImpl
@Inject constructor() : BaseInteractor<Entity2, Interactor.Callback<Entity2>>(),
    InternalInteractor {

    override fun initialize() {} // does initialization stuff!

}