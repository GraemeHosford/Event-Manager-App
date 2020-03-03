package graeme.hosford.eventmanager.presentation.event.list.common.model

import graeme.hosford.eventmanager.entity.event.Event
import graeme.hosford.eventmanager.presentation.common.model.UiModelListProcessor
import javax.inject.Inject

class EventListItemUiModelProcessor @Inject constructor(
    private val converter: EventListUiModelConverter
): UiModelListProcessor<Event, EventListItemUiModel>(converter)