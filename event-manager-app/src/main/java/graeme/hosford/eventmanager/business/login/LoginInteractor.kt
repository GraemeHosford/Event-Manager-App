package graeme.hosford.eventmanager.business.login

import graeme.hosford.eventmanager.business.common.Interactor
import graeme.hosford.eventmanager.business.user.CurrentUserInteractor

interface LoginInteractor : Interactor<LoginInteractor.SaveUserDetailsListener> {

    interface SaveUserDetailsListener {
        fun onSaveSuccess()

        fun onSaveFailure()
    }

    fun registerCurrentUserInteractorCallback(listener: CurrentUserInteractor.UserCompanyListener)

    fun checkUserHasCompany()

    fun loggedIn(): Boolean

    fun saveUserEmail(email: String?)

}