package graeme.hosford.eventmanager.presentation.login.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import graeme.hosford.eventmanager.EventManagerApplication
import graeme.hosford.eventmanager.presentation.login.LoginView
import graeme.hosford.eventmanager.presentation.login.SIGN_IN_REQUEST_CODE
import graeme.hosford.eventmanager.presentation.login.presentation.LoginPresenterImpl
import javax.inject.Inject

class LoginActivity : AppCompatActivity(),
    LoginView {

    @Inject
    lateinit var presenter: LoginPresenterImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventManagerApplication.appComponent.inject(this)

        presenter.onViewCreated(this)
        presenter.checkLoggedIn()
    }

    override fun showLoginFlow() {
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(presenter.getSignInProviders())
                .build(),
            SIGN_IN_REQUEST_CODE
        )
    }

    override fun showCompanyCreationFlow() {
        Toast.makeText(this, "Company flow", Toast.LENGTH_LONG).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        presenter.onActivityResult(requestCode, resultCode, data)
    }
}
