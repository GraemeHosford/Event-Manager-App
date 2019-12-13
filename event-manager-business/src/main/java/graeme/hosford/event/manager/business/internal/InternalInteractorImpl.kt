package graeme.hosford.event.manager.business.internal

import graeme.hosford.event.manager.business.BaseInteractor
import graeme.hosford.event.manager.business.Interactor
import javax.inject.Inject

internal class InternalInteractorImpl
@Inject constructor(
    private val entity1Repo: Entity1Repo,
    private val entity2Repo: Entity2Repo
) : BaseInteractor<Entity2, Interactor.Callback<Entity2>>(), InternalInteractor {

    override fun initialize() {} // does initialization stuff!

}