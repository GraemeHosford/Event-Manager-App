package graeme.hosford.eventmanager.presentation.company.detail.model

import graeme.hosford.eventmanager.entity.company.Member
import graeme.hosford.eventmanager.presentation.common.model.UiModelConverter
import javax.inject.Inject

class CompanyMemberUiModelConverter @Inject constructor() :
    UiModelConverter<Member, CompanyMemberUiModel> {

    override fun toUiModel(entity: Member): CompanyMemberUiModel {
        return CompanyMemberUiModel(entity.name)
    }
}