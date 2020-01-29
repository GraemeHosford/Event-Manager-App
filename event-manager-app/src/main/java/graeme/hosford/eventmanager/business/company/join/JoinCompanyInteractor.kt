package graeme.hosford.eventmanager.business.company.join

import graeme.hosford.eventmanager.business.common.Interactor

interface JoinCompanyInteractor : Interactor<JoinCompanyInteractor.JoinCompanyListener> {

    interface JoinCompanyListener {
        fun onJoinCompanySuccess(companyId: String)

        fun onJoinCompanyFailure()
    }

    fun addCurrentUserToCompany(id: String)

}