package graeme.hosford.eventmanager.business.login

import graeme.hosford.eventmanager.business.common.Interactor

interface LoginInteractor : Interactor {

    interface SaveUserDetailsListener {
        fun onSaveSuccess()

        fun onSaveFailure()
    }

    fun setUserDetailsListener(userDetailsListener: SaveUserDetailsListener)

    fun loggedIn(): Boolean

    fun saveUserDetails(email: String?)

}