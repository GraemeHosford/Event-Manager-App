package graeme.hosford.event.manager.app.core.login.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import graeme.hosford.event.manager.app.core.R
import graeme.hosford.event.manager.app.core.login.LoginView
import graeme.hosford.event.manager.app.core.login.SIGN_IN
import graeme.hosford.event.manager.app.core.login.presentation.LoginPresenterImpl
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), LoginView {

    @Inject
    lateinit var presenter: LoginPresenterImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter.onViewCreated(this)

        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(presenter.getSignInProviders())
                .build(), SIGN_IN
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        presenter.onActivityResult(requestCode, resultCode, data)
    }
}
