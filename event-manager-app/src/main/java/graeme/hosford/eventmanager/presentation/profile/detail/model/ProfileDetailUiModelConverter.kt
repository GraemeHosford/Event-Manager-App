package graeme.hosford.eventmanager.presentation.profile.detail.model

import graeme.hosford.eventmanager.entity.company.Person
import graeme.hosford.eventmanager.presentation.common.model.UiModelConverter
import javax.inject.Inject

class ProfileDetailUiModelConverter @Inject constructor() :
    UiModelConverter<Person, ProfileDetailUiModel> {

    override fun toUiModel(entity: Person): ProfileDetailUiModel {
        with(entity) {
            return ProfileDetailUiModel(
                "$firstName $lastName",
                id,
                imageUrlPath,
                jobTitle,
                description
            )
        }
    }
}