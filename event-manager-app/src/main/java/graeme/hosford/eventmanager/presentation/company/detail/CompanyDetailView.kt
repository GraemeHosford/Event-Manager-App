package graeme.hosford.eventmanager.presentation.company.detail

import graeme.hosford.eventmanager.presentation.common.view.recyclerview.RecyclerViewListView
import graeme.hosford.eventmanager.presentation.company.detail.model.CompanyMemberUiModel

const val MEMBER_ID_ARG = "Member_ID"
const val DEFAULT_MEMBER_ID_ARG = "DefMemberID"

interface CompanyDetailView : RecyclerViewListView<CompanyMemberUiModel> {
    fun showMemberDetail(id: String)
}