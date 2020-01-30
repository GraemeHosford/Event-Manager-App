package graeme.hosford.eventmanager.presentation.login

import com.firebase.ui.auth.AuthUI

interface LoginPresenter {

    fun checkLoggedIn()

    fun getSignInProviders(): List<AuthUI.IdpConfig>

}