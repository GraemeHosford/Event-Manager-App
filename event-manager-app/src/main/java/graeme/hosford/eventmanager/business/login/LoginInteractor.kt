package graeme.hosford.eventmanager.business.login

import graeme.hosford.eventmanager.business.common.Interactor

interface LoginInteractor : Interactor<LoginInteractor.SaveUserDetailsListener> {

    interface SaveUserDetailsListener {
        fun onSaveSuccess()

        fun onSaveFailure()
    }

    fun loggedIn(): Boolean

    fun saveUserEmail(email: String?)

}