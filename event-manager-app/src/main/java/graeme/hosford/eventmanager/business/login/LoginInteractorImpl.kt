package graeme.hosford.eventmanager.business.login

import graeme.hosford.eventmanager.business.common.BaseInteractor
import graeme.hosford.eventmanager.data.login.CurrentUserNetworkAccess
import javax.inject.Inject

class LoginInteractorImpl @Inject constructor(
    private val currentUserNetworkAccess: CurrentUserNetworkAccess
) : BaseInteractor(), LoginInteractor {

    private var userDetailsListener: LoginInteractor.SaveUserDetailsListener? = null

    override fun onCreate() {
        super.onCreate()
        currentUserNetworkAccess.setEmailSaveListener(SaveListener())
    }

    override fun setUserDetailsListener(userDetailsListener: LoginInteractor.SaveUserDetailsListener) {
        this.userDetailsListener = userDetailsListener
    }

    override fun loggedIn(): Boolean {
        return currentUserNetworkAccess.getCurrentUser() != null
    }

    override fun saveUserDetails(email: String?) {
        if (email != null) {
            currentUserNetworkAccess.saveUserInfo(email)
        } else {
            userDetailsListener?.onSaveFailure()
        }
    }

    private inner class SaveListener : CurrentUserNetworkAccess.EmailSaveListener {
        override fun onEmailSaveSuccess() {
            userDetailsListener?.onSaveSuccess()
        }

        override fun onEmailSaveFailure() {
            userDetailsListener?.onSaveFailure()
        }
    }
}