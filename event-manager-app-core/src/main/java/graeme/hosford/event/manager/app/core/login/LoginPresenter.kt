package graeme.hosford.event.manager.app.core.login

import com.firebase.ui.auth.AuthUI

interface LoginPresenter {

    fun getSignInProviders(): List<AuthUI.IdpConfig>

}