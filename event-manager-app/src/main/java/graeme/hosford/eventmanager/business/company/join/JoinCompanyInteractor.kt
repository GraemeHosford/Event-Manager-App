package graeme.hosford.eventmanager.business.company.join

import graeme.hosford.eventmanager.business.common.Interactor
import graeme.hosford.eventmanager.business.user.CurrentUserInteractor

interface JoinCompanyInteractor : Interactor<JoinCompanyInteractor.JoinCompanyListener> {

    interface JoinCompanyListener {
        fun onJoinCompanySuccess(companyId: String)

        fun onJoinCompanyFailure()
    }

    fun registerCurrentUserInteractorCallback(listener: CurrentUserInteractor.UserCompanyListener)

    fun setUserCompany(companyId: String)

    fun addCurrentUserToCompany(id: String)

}