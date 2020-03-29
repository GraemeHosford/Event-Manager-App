package graeme.hosford.eventmanager.presentation.attendees.choose.model

import graeme.hosford.eventmanager.entity.company.Person
import graeme.hosford.eventmanager.presentation.common.model.UiModelConverter
import javax.inject.Inject

class AttendeesUiModelConverter @Inject constructor() : UiModelConverter<Person, AttendeesUiModel> {

    override fun toUiModel(entity: Person): AttendeesUiModel {
        return AttendeesUiModel(
            entity.id,
            "${entity.firstName} ${entity.lastName}",
            entity.imageUrlPath,
            entity.jobTitle
        )
    }
}