package graeme.hosford.eventmanager.presentation.company.detail.model

import graeme.hosford.eventmanager.entity.company.Member
import graeme.hosford.eventmanager.presentation.common.model.UiModelListProcessor
import javax.inject.Inject

class CompanyMemberUiModelProcessor @Inject constructor(
    private val converter: CompanyMemberUiModelConverter
) : UiModelListProcessor<Member, CompanyMemberUiModel>(converter)