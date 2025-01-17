package graeme.hosford.eventmanager.presentation.event.list.invited.presentation

import graeme.hosford.eventmanager.business.event.list.invited.EventListInvitedInteractor
import graeme.hosford.eventmanager.presentation.event.list.common.model.EventListItemUiModelProcessor
import graeme.hosford.eventmanager.presentation.event.list.common.presentation.BaseEventListPresenterImpl
import graeme.hosford.eventmanager.presentation.event.list.invited.EventListInvitedPresenter
import javax.inject.Inject

class EventListInvitedPresenterImpl @Inject constructor(
    private val processor: EventListItemUiModelProcessor,
    private val interactor: EventListInvitedInteractor
) : BaseEventListPresenterImpl(processor, interactor), EventListInvitedPresenter {

    override fun onResume() {
        super.onResume()
        getInvitedEvents()
    }

    override fun getInvitedEvents() {
        interactor.getInvitedEvents()
    }

    override fun onGoingResponseClick(eventId: String) {
        interactor.updateAttendingStatus(eventId, true)
    }

    override fun onNotGoingResponseClick(eventId: String) {
        interactor.updateAttendingStatus(eventId, false)
    }
}