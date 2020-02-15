package graeme.hosford.eventmanager.presentation.attendees.model

import graeme.hosford.eventmanager.entity.company.Member
import graeme.hosford.eventmanager.presentation.common.model.UiModelConverter
import javax.inject.Inject

class AttendeesuiModelConverter @Inject constructor() : UiModelConverter<Member, AttendeesUiModel> {

    override fun toUiModel(entity: Member): AttendeesUiModel {
        return AttendeesUiModel(entity.name)
    }
}