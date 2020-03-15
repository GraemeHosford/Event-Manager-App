package graeme.hosford.eventmanager.business.user

import graeme.hosford.eventmanager.business.common.BaseInteractor
import graeme.hosford.eventmanager.data.login.CurrentUserNetworkAccess
import javax.inject.Inject

class CurrentUserInteractorImpl @Inject constructor(
    private val currentUserNetworkAccess: CurrentUserNetworkAccess
) : BaseInteractor<CurrentUserInteractor.UserCompanyListener>(),
    CurrentUserInteractor {

    override fun onCreate() {
        super.onCreate()
        currentUserNetworkAccess.setUserInfoSavedListener(UserInfoSavedListener())
        currentUserNetworkAccess.setUserInfoRetrievedListener(UserInfoRetrievedListener())
    }

    override fun setUserCompany(companyId: String) {
        currentUserNetworkAccess.saveUserInfo(
            currentUserNetworkAccess.getCurrentUser()?.email!!,
            hashMapOf(
                "companyId" to companyId
            )
        )
    }

    override fun getUserCompanyId() {
        currentUserNetworkAccess.getUserInfo(
            currentUserNetworkAccess.getCurrentUser()?.email!!,
            "companyId"
        )
    }

    override fun checkUserHasCompany() {
        currentUserNetworkAccess.getUserInfo(
            currentUserNetworkAccess.getCurrentUser()?.email!!,
            "companyId"
        )
    }

    override fun getCurrentUserId(): String {
        return currentUserNetworkAccess.getCurrentUser()!!.email!!
    }

    private inner class UserInfoSavedListener : CurrentUserNetworkAccess.UserInfoSavedCallback {
        override fun onUserInfoSavedSuccess() {
            callback?.onAddUserCompanySuccess()
        }

        override fun onUserInfoSavedFailure() {
            callback?.onAddUserCompanyFailure()
        }
    }

    private inner class UserInfoRetrievedListener :
        CurrentUserNetworkAccess.UserInfoRetrievedCallback {
        override fun onUserInfoRetrieved(info: Any?) {
            callback?.onUserInfoRetrieved(info)
        }

        override fun onUserInfoRetrievalFailure() {
            callback?.onUserInfoRetrievalFailed()
        }
    }

}