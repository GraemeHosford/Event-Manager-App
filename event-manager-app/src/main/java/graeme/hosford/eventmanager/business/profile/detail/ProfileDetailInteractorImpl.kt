package graeme.hosford.eventmanager.business.profile.detail

import graeme.hosford.eventmanager.business.common.BaseInteractor
import graeme.hosford.eventmanager.data.login.CurrentUserNetworkAccess
import graeme.hosford.eventmanager.entity.company.Person
import javax.inject.Inject

class ProfileDetailInteractorImpl @Inject constructor(
    private val userNetworkAccess: CurrentUserNetworkAccess
) : BaseInteractor<ProfileDetailInteractor.ProfileDetailCallback>(),
    ProfileDetailInteractor {

    override fun onCreate() {
        super.onCreate()
        userNetworkAccess.setUserInfoRetrievedListener(UserInfoRetrievedListener())
    }

    override fun getUserDetails(personId: String) {
        userNetworkAccess.getFullUserInfo(personId)
    }

    override fun getCurrentUserProfile() {
        userNetworkAccess.getFullUserInfo(
            userNetworkAccess.getCurrentUser()!!.email!!
        )
    }

    private inner class UserInfoRetrievedListener :
        CurrentUserNetworkAccess.UserInfoRetrievedCallback {
        override fun onUserInfoRetrieved(info: Any?) {
            callback?.onUserRetrieved(info as Person)
        }

        override fun onUserInfoRetrievalFailure() {
            callback?.onUserRetrieveFail()
        }
    }
}