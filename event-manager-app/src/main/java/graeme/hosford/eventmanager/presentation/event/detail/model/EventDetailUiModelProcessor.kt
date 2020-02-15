package graeme.hosford.eventmanager.presentation.event.detail.model

import graeme.hosford.eventmanager.entity.event.Event
import graeme.hosford.eventmanager.presentation.common.model.UiModelSingleProcessor
import javax.inject.Inject

class EventDetailUiModelProcessor @Inject constructor(
    converter: EventDetailUiModelConverter
) : UiModelSingleProcessor<Event, EventDetailUiModel>(converter)