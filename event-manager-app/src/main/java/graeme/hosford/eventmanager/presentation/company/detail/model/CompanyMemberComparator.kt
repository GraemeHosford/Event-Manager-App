package graeme.hosford.eventmanager.presentation.company.detail.model

object CompanyMemberComparator {

    val memberNameComparator = compareBy(CompanyMemberUiModel::personName)

}