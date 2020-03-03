package graeme.hosford.eventmanager.presentation.company.detail.model

import graeme.hosford.eventmanager.entity.company.Person
import graeme.hosford.eventmanager.presentation.common.model.UiModelConverter
import javax.inject.Inject

class CompanyMemberUiModelConverter @Inject constructor() :
    UiModelConverter<Person, CompanyMemberUiModel> {

    override fun toUiModel(entity: Person): CompanyMemberUiModel {
        return CompanyMemberUiModel(entity.name)
    }
}