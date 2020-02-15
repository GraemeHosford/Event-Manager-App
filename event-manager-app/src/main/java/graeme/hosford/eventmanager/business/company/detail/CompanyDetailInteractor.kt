package graeme.hosford.eventmanager.business.company.detail

import graeme.hosford.eventmanager.business.common.Interactor
import graeme.hosford.eventmanager.entity.company.Member

interface CompanyDetailInteractor : Interactor<CompanyDetailInteractor.CompanyDetailCallback> {

    interface CompanyDetailCallback {
        fun onUserCompanyIdRetrieved(companyId: String)

        fun onMembersRetrieved(members: List<Member>)

        fun onDataLoadFailure()
    }

    fun getCompanyMembers()

}