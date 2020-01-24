package graeme.hosford.eventmanager.business.company.join

import graeme.hosford.eventmanager.business.common.Interactor

interface JoinCompanyInteractor : Interactor<JoinCompanyInteractor.JoinCompanyListener> {

    interface JoinCompanyListener {
        fun onJoinCompanySuccess()

        fun onJoinCompanyFailure()
    }

    fun addCurrentUserToCompany(id: Int)

}