package graeme.hosford.event.manager.app.core.login.presentation

import android.app.Activity
import android.content.Intent
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import graeme.hosford.event.manager.app.core.common.BasePresenter
import graeme.hosford.event.manager.app.core.login.LoginPresenter
import graeme.hosford.event.manager.app.core.login.LoginView
import graeme.hosford.event.manager.app.core.login.SIGN_IN
import javax.inject.Inject

class LoginPresenterImpl @Inject constructor() : BasePresenter<LoginView>(), LoginPresenter {

    override fun getSignInProviders(): List<AuthUI.IdpConfig> {
        return listOf(AuthUI.IdpConfig.EmailBuilder().build())
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                /* Sign in success */
            }
        }
    }
}