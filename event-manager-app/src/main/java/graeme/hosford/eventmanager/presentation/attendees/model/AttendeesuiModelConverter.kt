package graeme.hosford.eventmanager.presentation.attendees.model

import graeme.hosford.eventmanager.entity.company.Person
import graeme.hosford.eventmanager.presentation.common.model.UiModelConverter
import javax.inject.Inject

class AttendeesuiModelConverter @Inject constructor() : UiModelConverter<Person, AttendeesUiModel> {

    override fun toUiModel(entity: Person): AttendeesUiModel {
        return AttendeesUiModel(entity.firstName)
    }
}