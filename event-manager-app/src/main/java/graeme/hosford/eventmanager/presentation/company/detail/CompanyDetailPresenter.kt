package graeme.hosford.eventmanager.presentation.company.detail

import graeme.hosford.eventmanager.presentation.common.presenter.Presenter

interface CompanyDetailPresenter : Presenter<CompanyDetailView> {
    fun getCompanyMembers()

    fun onCompanyMemberClick(id: String)
}