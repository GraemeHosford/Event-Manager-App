package graeme.hosford.eventmanager.presentation.login.presentation

import android.app.Activity
import android.content.Intent
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.business.login.LoginInteractor
import graeme.hosford.eventmanager.business.profile.create.CreateProfileInteractor
import graeme.hosford.eventmanager.business.user.CurrentUserInteractor
import graeme.hosford.eventmanager.entity.company.Person
import graeme.hosford.eventmanager.presentation.common.presenter.BasePresenter
import graeme.hosford.eventmanager.presentation.login.LoginPresenter
import graeme.hosford.eventmanager.presentation.login.LoginView
import graeme.hosford.eventmanager.presentation.login.SIGN_IN_REQUEST_CODE
import javax.inject.Inject

class LoginPresenterImpl @Inject constructor(
    private val interactor: LoginInteractor,
    private val profileInteractor: CreateProfileInteractor
) : BasePresenter<LoginView, LoginInteractor>(interactor),
    LoginPresenter {

    override fun onViewCreated(view: LoginView) {
        super.onViewCreated(view)
        interactor.registerCallback(UserDetailsSaveListener())
        interactor.registerCurrentUserInteractorCallback(UserInfoRetrieved())

        profileInteractor.registerCallback(ProfileInteractorCallback())
    }

    override fun checkLoggedIn() {
        if (interactor.loggedIn()) {
            profileInteractor.checkProfileExists()
        } else {
            view?.showLoginFlow()
        }
    }

    override fun getSignInProviders(): List<AuthUI.IdpConfig> {
        return listOf(AuthUI.IdpConfig.EmailBuilder().build())
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SIGN_IN_REQUEST_CODE) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK && response != null) {
                interactor.saveUserEmail(response.email)
            } else {
                signInError()
            }
        }
    }

    private fun signInError() {
        view?.showLongToast(R.string.error_sign_in)
        view?.showLoginFlow()
    }

    private inner class UserDetailsSaveListener : LoginInteractor.SaveUserDetailsListener {
        override fun onSaveSuccess() {
            interactor.checkUserHasCompany()
        }

        override fun onSaveFailure() {
            signInError()
        }
    }

    private inner class UserInfoRetrieved : CurrentUserInteractor.UserCompanyListener {
        override fun onAddUserCompanySuccess() {
            /* This callback method name does not make much sense here and comes from previous
            changes made where this method name was not updated. This callback interface should be
            split up more but do not have time for that refactoring right now :/ */
            interactor.checkUserHasCompany()
        }

        override fun onAddUserCompanyFailure() {
            signInError()
        }

        override fun onUserInfoRetrieved(info: Any?) {
            if (info != null) {
                if (info is Person && info.firstName == "") {
                    view?.showProfileCreationFlow()
                } else {
                    view?.showMainActivity()
                }
            } else {
                view?.showCompanyCreationFlow()
            }
        }

        override fun onUserInfoRetrievalFailed() {
            view?.showLongToast(R.string.generic_error_loading_data)
        }
    }

    private inner class ProfileInteractorCallback : CreateProfileInteractor.CreateProfileCallback {
        override fun onUserInfoSavedSuccessfully() {

        }

        override fun onUserInfoSaveFailed() {
        }

        override fun onProfileInfoRetrieved(data: Person) {
            if (data.firstName == "") {
                view?.showProfileCreationFlow()
            } else {
                interactor.checkUserHasCompany()
            }
        }

        override fun onProfileInfoNotRetrieved() {
            view?.showProfileCreationFlow()
        }
    }
}