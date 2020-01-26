package graeme.hosford.eventmanager.business.user

import graeme.hosford.eventmanager.business.common.BaseInteractor
import graeme.hosford.eventmanager.data.login.CurrentUserNetworkAccess
import javax.inject.Inject

class CurrentUserInteractorImpl @Inject constructor(
    private val currentUserNetworkAccess: CurrentUserNetworkAccess
) : BaseInteractor<CurrentUserInteractor.AddUserCompanyListener>(),
    CurrentUserInteractor {

    override fun onCreate() {
        super.onCreate()
        currentUserNetworkAccess.setAddUserCompanyListener(AddUserCompanyListener())
    }

    override fun setUserCompany(companyId: String) {
        currentUserNetworkAccess.setUserCompany(
            currentUserNetworkAccess.getCurrentUser()?.email!!,
            companyId
        )
    }

    private inner class AddUserCompanyListener : CurrentUserNetworkAccess.AddUserCompanyListener {
        override fun onAddUserCompanySuccess() {
            callback?.onAddUserCompanySuccess()
        }

        override fun onAddUserCompanyFailure() {
            callback?.onAddUserCompanyFailure()
        }
    }

}