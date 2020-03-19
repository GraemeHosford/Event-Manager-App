package graeme.hosford.eventmanager.presentation.event.list.common.model

import graeme.hosford.eventmanager.entity.event.Event
import graeme.hosford.eventmanager.presentation.common.model.UiModelConverter
import javax.inject.Inject

class EventListUiModelConverter @Inject constructor() :
    UiModelConverter<Event, EventListItemUiModel> {

    override fun toUiModel(entity: Event): EventListItemUiModel {
        return EventListItemUiModel(
            entity.id,
            entity.owner,
            entity.name,
            entity.description,
            entity.startDate,
            entity.endDate,
            entity.locationName,
            entity.attendees
        )
    }
}