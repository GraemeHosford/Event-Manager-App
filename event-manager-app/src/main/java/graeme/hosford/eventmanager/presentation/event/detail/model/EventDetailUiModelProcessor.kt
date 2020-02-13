package graeme.hosford.eventmanager.presentation.event.detail.model

import graeme.hosford.eventmanager.entity.event.Event
import graeme.hosford.eventmanager.presentation.common.model.UiModelConverter
import graeme.hosford.eventmanager.presentation.common.model.UiModelSingleProcessor
import javax.inject.Inject

class EventDetailUiModelProcessor @Inject constructor(
    converter: UiModelConverter<Event, EventDetailUiModel>
) : UiModelSingleProcessor<Event, EventDetailUiModel>(converter)