package graeme.hosford.eventmanager.presentation.event.list.attending.presentation

import graeme.hosford.eventmanager.business.event.list.attending.EventListAttendingInteractor
import graeme.hosford.eventmanager.presentation.event.list.attending.EventListAttendingPresenter
import graeme.hosford.eventmanager.presentation.event.list.common.model.EventListItemUiModelProcessor
import graeme.hosford.eventmanager.presentation.event.list.common.presentation.BaseEventListPresenterImpl
import javax.inject.Inject

class EventListAttendingPresenterImpl @Inject constructor(
    processor: EventListItemUiModelProcessor,
    private val interactor: EventListAttendingInteractor
) : BaseEventListPresenterImpl(processor, interactor), EventListAttendingPresenter {

    override fun onResume() {
        super.onResume()
        getAttendingEvents()
    }

    override fun getAttendingEvents() {
        interactor.getAttendingEvents()
    }
}