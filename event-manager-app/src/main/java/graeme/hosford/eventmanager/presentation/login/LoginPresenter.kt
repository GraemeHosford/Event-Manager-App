package graeme.hosford.eventmanager.presentation.login

import com.firebase.ui.auth.AuthUI
import graeme.hosford.eventmanager.presentation.common.presenter.Presenter

interface LoginPresenter : Presenter<LoginView> {

    fun checkLoggedIn()

    fun getSignInProviders(): List<AuthUI.IdpConfig>

}