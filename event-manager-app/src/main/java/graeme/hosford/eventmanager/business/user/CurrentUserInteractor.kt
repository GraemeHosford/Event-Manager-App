package graeme.hosford.eventmanager.business.user

import graeme.hosford.eventmanager.business.common.Interactor

interface CurrentUserInteractor : Interactor<CurrentUserInteractor.AddUserCompanyListener> {

    interface AddUserCompanyListener {
        fun onAddUserCompanySuccess()

        fun onAddUserCompanyFailure()
    }

    fun setUserCompany(companyId: String)

}