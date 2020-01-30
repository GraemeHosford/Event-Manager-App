package graeme.hosford.eventmanager.business.login

import graeme.hosford.eventmanager.business.common.BaseInteractor
import graeme.hosford.eventmanager.business.common.Interactor
import graeme.hosford.eventmanager.business.user.CurrentUserInteractor
import graeme.hosford.eventmanager.data.login.CurrentUserNetworkAccess
import javax.inject.Inject

class LoginInteractorImpl @Inject constructor(
    private val currentUserNetworkAccess: CurrentUserNetworkAccess,
    private val currentUserInteractor: CurrentUserInteractor
) : BaseInteractor<LoginInteractor.SaveUserDetailsListener>(), LoginInteractor {

    override fun onCreate() {
        super.onCreate()
        currentUserNetworkAccess.setUserInfoSavedListener(UserInfoSavedListener())
    }

    override fun registerManagedInteractors(): List<Interactor<*>> = listOf(currentUserInteractor)

    override fun loggedIn(): Boolean {
        return currentUserNetworkAccess.getCurrentUser() != null
    }

    override fun registerCurrentUserInteractorCallback(listener: CurrentUserInteractor.UserCompanyListener) {
        currentUserInteractor.registerCallback(listener)
    }

    override fun checkUserHasCompany() {
        currentUserInteractor.checkUserHasCompany()
    }

    override fun saveUserEmail(email: String?) {
        if (email != null) {
            currentUserNetworkAccess.saveUserInfo(
                email,
                hashMapOf(
                    "userEmail" to email
                )
            )
        } else {
            callback?.onSaveFailure()
        }
    }

    private inner class UserInfoSavedListener : CurrentUserNetworkAccess.UserInfoSavedCallback {
        override fun onUserInfoSavedSuccess() {
            callback?.onSaveSuccess()
        }

        override fun onUserInfoSavedFailure() {
            callback?.onSaveFailure()
        }
    }
}