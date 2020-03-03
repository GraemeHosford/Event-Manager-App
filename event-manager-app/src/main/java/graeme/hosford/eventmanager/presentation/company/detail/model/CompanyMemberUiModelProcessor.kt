package graeme.hosford.eventmanager.presentation.company.detail.model

import graeme.hosford.eventmanager.entity.company.Person
import graeme.hosford.eventmanager.presentation.common.model.UiModelListProcessor
import javax.inject.Inject

class CompanyMemberUiModelProcessor @Inject constructor(
    private val converter: CompanyMemberUiModelConverter
) : UiModelListProcessor<Person, CompanyMemberUiModel>(converter)