package graeme.hosford.eventmanager.presentation.event.list.attending.presentation

import graeme.hosford.eventmanager.business.event.list.EventListInteractor
import graeme.hosford.eventmanager.presentation.event.list.attending.EventListAttendingPresenter
import graeme.hosford.eventmanager.presentation.event.list.common.model.EventListItemUiModelProcessor
import graeme.hosford.eventmanager.presentation.event.list.common.presentation.BaseEventListPresenterImpl
import javax.inject.Inject

class EventListAttendingPresenterImpl @Inject constructor(
    private val processor: EventListItemUiModelProcessor,
    private val interactor: EventListInteractor
) : BaseEventListPresenterImpl(processor, interactor), EventListAttendingPresenter