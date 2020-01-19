package graeme.hosford.eventmanager.business.login

import graeme.hosford.eventmanager.business.common.BaseInteractor
import graeme.hosford.eventmanager.data.login.CurrentUserNetworkAccess
import javax.inject.Inject

class LoginInteractorImpl @Inject constructor(
    private val currentUserNetworkAccess: CurrentUserNetworkAccess
) : BaseInteractor<LoginInteractor.SaveUserDetailsListener>(), LoginInteractor {

    override fun onCreate() {
        super.onCreate()
        currentUserNetworkAccess.setEmailSaveListener(SaveListener())
    }

    override fun loggedIn(): Boolean {
        return currentUserNetworkAccess.getCurrentUser() != null
    }

    override fun saveUserDetails(email: String?) {
        if (email != null) {
            currentUserNetworkAccess.saveUserInfo(email)
        } else {
            callback?.onSaveFailure()
        }
    }

    private inner class SaveListener : CurrentUserNetworkAccess.EmailSaveListener {
        override fun onEmailSaveSuccess() {
            callback?.onSaveSuccess()
        }

        override fun onEmailSaveFailure() {
            callback?.onSaveFailure()
        }
    }
}