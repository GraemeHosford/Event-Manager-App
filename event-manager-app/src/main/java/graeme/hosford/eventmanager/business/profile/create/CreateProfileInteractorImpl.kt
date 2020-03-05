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
        currentUserNetworkAccess.setProfileImageUploadedCallback(ProfileImageSavedCallback())
    }

    override fun saveUserProfileImage(imageBytes: ByteArray) {
        currentUserNetworkAccess.saveUserProfileImage(imageBytes)
    }

    override fun saveUserProfileData(
        firstName: String,
        lastName: String,
        jobTitle: String,
        description: String,
        imageUrlPath: String
    ) {
        currentUserNetworkAccess.saveUserInfo(
            currentUserNetworkAccess.getCurrentUser()!!.email!!,
            hashMapOf(
                Person.FIRST_NAME to firstName,
                Person.LAST_NAME to lastName,
                Person.JOB_TITLE to jobTitle,
                Person.DESCRIPTION to description,
                Person.IMAGE_URL_PATH to imageUrlPath
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
            callback?.onProfileInfoRetrieved(info as Person)
        }

        override fun onUserInfoRetrievalFailure() {
            callback?.onProfileInfoNotRetrieved()
        }
    }

    private inner class ProfileImageSavedCallback :
        CurrentUserNetworkAccess.ProfileImageUploadedCallback {
        override fun onProfileImageUploadedSuccessfully(imagePath: String) {
            callback?.onProfileImageSaved(imagePath)
        }

        override fun onProfileImageUploadFailed() {
            callback?.onProfileImageSaveFailed()
        }
    }
}