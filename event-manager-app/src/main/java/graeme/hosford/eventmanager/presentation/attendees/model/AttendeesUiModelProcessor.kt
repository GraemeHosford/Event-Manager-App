package graeme.hosford.eventmanager.presentation.attendees.model

import graeme.hosford.eventmanager.entity.company.Person
import graeme.hosford.eventmanager.presentation.common.model.UiModelListProcessor
import javax.inject.Inject

class AttendeesUiModelProcessor @Inject constructor(
    private val converter: AttendeesuiModelConverter
) : UiModelListProcessor<Person, AttendeesUiModel>(converter)