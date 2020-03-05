package graeme.hosford.eventmanager.presentation.profile.detail.model

import graeme.hosford.eventmanager.entity.company.Person
import graeme.hosford.eventmanager.presentation.common.model.UiModelSingleProcessor
import javax.inject.Inject

class ProfileDetailUiModelProcessor @Inject constructor(
    private val converter: ProfileDetailUiModelConverter
) : UiModelSingleProcessor<Person, ProfileDetailUiModel>(converter)