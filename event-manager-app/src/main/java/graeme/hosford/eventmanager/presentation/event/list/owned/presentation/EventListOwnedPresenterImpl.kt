package graeme.hosford.eventmanager.presentation.event.list.owned.presentation

import graeme.hosford.eventmanager.business.event.list.owned.EventListOwnedInteractor
import graeme.hosford.eventmanager.presentation.event.list.common.model.EventListItemUiModelProcessor
import graeme.hosford.eventmanager.presentation.event.list.common.presentation.BaseEventListPresenterImpl
import graeme.hosford.eventmanager.presentation.event.list.owned.EventListOwnedPresenter
import javax.inject.Inject

class EventListOwnedPresenterImpl @Inject constructor(
    processor: EventListItemUiModelProcessor,
    private val interactor: EventListOwnedInteractor
) : BaseEventListPresenterImpl(processor, interactor), EventListOwnedPresenter {

    override fun onResume() {
        super.onResume()
        getOwnedEvents()
    }

    override fun getOwnedEvents() {
        interactor.getOwnedEvents()
    }
}