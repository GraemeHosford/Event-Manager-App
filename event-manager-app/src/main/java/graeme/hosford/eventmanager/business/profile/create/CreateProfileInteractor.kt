package graeme.hosford.eventmanager.business.profile.create

import graeme.hosford.eventmanager.business.common.Interactor
import graeme.hosford.eventmanager.entity.company.Person

interface CreateProfileInteractor : Interactor<CreateProfileInteractor.CreateProfileCallback> {

    interface CreateProfileCallback {
        fun onUserInfoSavedSuccessfully()

        fun onUserInfoSaveFailed()

        fun onProfileInfoRetrieved(data: Person)

        fun onProfileInfoNotRetrieved()
    }

    fun saveUserProfileData(
        firstName: String,
        lastName: String,
        jobTitle: String,
        description: String
    )

    fun checkProfileExists()

}