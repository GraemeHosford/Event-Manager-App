package graeme.hosford.eventmanager.business.profile.create

import graeme.hosford.eventmanager.business.common.Interactor

interface CreateProfileInteractor : Interactor<CreateProfileInteractor.CreateProfileCallback> {

    interface CreateProfileCallback {
        fun onUserInfoSavedSuccessfully()

        fun onUserInfoSaveFailed()
    }

    fun saveUserProfileData(
        firstName: String,
        lastName: String,
        jobTitle: String,
        description: String
    )

}