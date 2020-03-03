package graeme.hosford.eventmanager.presentation.login.view

import android.content.Intent
import android.os.Bundle
import com.firebase.ui.auth.AuthUI
import graeme.hosford.eventmanager.EventManagerApplication
import graeme.hosford.eventmanager.presentation.common.view.activity.BaseActivity
import graeme.hosford.eventmanager.presentation.company.create_join_screen.view.CreateJoinCompanyActivity
import graeme.hosford.eventmanager.presentation.login.LoginPresenter
import graeme.hosford.eventmanager.presentation.login.LoginView
import graeme.hosford.eventmanager.presentation.login.SIGN_IN_REQUEST_CODE
import graeme.hosford.eventmanager.presentation.main.view.MainActivity
import graeme.hosford.eventmanager.presentation.profile.create.view.CreateProfileActivity
import javax.inject.Inject

/* Calendar view for seeing all events to see event schedule
* Event types for filtering.
* Restricted event attendees, all invited but limited spaces */

class LoginActivity : BaseActivity(),
    LoginView {

    @Inject
    lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventManagerApplication.appComponent.inject(this)

        presenter.onViewCreated(this)
        presenter.checkLoggedIn()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }

    override fun showLoginFlow() {
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setIsSmartLockEnabled(false, true)
                .setAvailableProviders(presenter.getSignInProviders())
                .build(),
            SIGN_IN_REQUEST_CODE
        )
    }

    override fun showProfileCreationFlow() {
        startActivity(Intent(this, CreateProfileActivity::class.java))
        finish()
    }

    override fun showCompanyCreationFlow() {
        startActivity(Intent(this, CreateJoinCompanyActivity::class.java))
        finish()
    }

    override fun showMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        presenter.onActivityResult(requestCode, resultCode, data)
    }
}
