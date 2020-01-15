package graeme.hosford.eventmanager.presentation.login.presentation

import android.app.Activity
import android.content.Intent
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import graeme.hosford.eventmanager.business.login.LoginInteractor
import graeme.hosford.eventmanager.presentation.common.presenter.BasePresenter
import graeme.hosford.eventmanager.presentation.login.LoginPresenter
import graeme.hosford.eventmanager.presentation.login.LoginView
import graeme.hosford.eventmanager.presentation.login.SIGN_IN_REQUEST_CODE
import javax.inject.Inject

class LoginPresenterImpl @Inject constructor(
    private val interactor: LoginInteractor
) : BasePresenter<LoginView>(),
    LoginPresenter {

    override fun checkLoggedIn() {
        if (interactor.loggedIn()) {
            view.showCompanyCreationFlow()
        } else {
            view.showLoginFlow()
        }
    }

    override fun getSignInProviders(): List<AuthUI.IdpConfig> {
        return listOf(AuthUI.IdpConfig.EmailBuilder().build())
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SIGN_IN_REQUEST_CODE) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                /* Get user info from response here and save to Firestore */
                view.showCompanyCreationFlow()
            } else {
                view.showLongToast("Error on signing in")
            }
        }
    }
}