package graeme.hosford.eventmanager.business.profile.detail

import graeme.hosford.eventmanager.business.common.Interactor
import graeme.hosford.eventmanager.entity.company.Person

interface ProfileDetailInteractor : Interactor<ProfileDetailInteractor.ProfileDetailCallback> {

    interface ProfileDetailCallback {
        fun onUserRetrieved(user: Person)

        fun onUserRetrieveFail()
    }

    fun getUserDetails(personId: String)

    fun getCurrentUserProfile()

}