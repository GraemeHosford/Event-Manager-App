package graeme.hosford.eventmanager.business.profile.create

import graeme.hosford.eventmanager.business.common.BaseInteractor
import graeme.hosford.eventmanager.data.login.CurrentUserNetworkAccess
import graeme.hosford.eventmanager.entity.company.Person
import javax.inject.Inject

class CreateProfileInteractorImpl @Inject constructor(
    private val currentUserNetworkAccess: CurrentUserNetworkAccess
) : BaseInteractor<CreateProfileInteractor.CreateProfileCallback>(),
    CreateProfileInteractor {

    override fun onCreate() {
        super.onCreate()
        currentUserNetworkAccess.setUserInfoSavedListener(UserInfoSavedListener())
        currentUserNetworkAccess.setUserInfoRetrievedListener(UserInfoRetrievedListener())
    }

    override fun saveUserProfileData(
        firstName: String,
        lastName: String,
        jobTitle: String,
        description: String
    ) {
        currentUserNetworkAccess.saveUserInfo(
            currentUserNetworkAccess.getCurrentUser()!!.email!!,
            hashMapOf(
                Person.FIRST_NAME to firstName,
                Person.LAST_NAME to lastName,
                Person.JOB_TITLE to jobTitle,
                Person.DESCRIPTION to description
            )
        )
    }

    override fun checkProfileExists() {
        currentUserNetworkAccess.getFullUserInfo(
            currentUserNetworkAccess.getCurrentUser()!!.email!!
        )
    }

    private inner class UserInfoSavedListener: CurrentUserNetworkAccess.UserInfoSavedCallback {
        override fun onUserInfoSavedSuccess() {
            callback?.onUserInfoSavedSuccessfully()
        }

        override fun onUserInfoSavedFailure() {
            callback?.onUserInfoSaveFailed()
        }
    }

    private inner class UserInfoRetrievedListener :
        CurrentUserNetworkAccess.UserInfoRetrievedCallback {
        override fun onUserInfoRetrieved(info: Any?) {

        }

        override fun onUserInfoRetrievalFailure() {

        }
    }
}