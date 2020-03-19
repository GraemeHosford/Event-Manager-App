package graeme.hosford.eventmanager.presentation.event.detail.model

import com.google.android.gms.maps.model.LatLng
import graeme.hosford.eventmanager.entity.event.Event
import graeme.hosford.eventmanager.presentation.common.model.UiModelConverter
import javax.inject.Inject

class EventDetailUiModelConverter @Inject constructor() :
    UiModelConverter<Event, EventDetailUiModel> {

    override fun toUiModel(entity: Event): EventDetailUiModel {
        return EventDetailUiModel(
            entity.id,
            entity.name,
            entity.description,
            entity.startDate,
            entity.endDate,
            entity.locationName,
            LatLng(entity.locationLat, entity.locationLong),
            entity.invitees,
            entity.attendees
        )
    }
}