package graeme.hosford.eventmanager.business.user

import graeme.hosford.eventmanager.business.common.Interactor

interface CurrentUserInteractor : Interactor<CurrentUserInteractor.UserCompanyListener> {

    interface UserCompanyListener {
        fun onAddUserCompanySuccess()

        fun onAddUserCompanyFailure()

        fun onUserInfoRetrieved(info: Any?)

        fun onUserInfoRetrievalFailed()
    }

    fun setUserCompany(companyId: String)

    fun checkUserHasCompany()

}