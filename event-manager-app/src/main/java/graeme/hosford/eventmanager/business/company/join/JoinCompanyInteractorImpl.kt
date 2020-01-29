package graeme.hosford.eventmanager.business.company.join

import graeme.hosford.eventmanager.business.common.BaseInteractor
import graeme.hosford.eventmanager.data.company.CompanyFirebaseAccess
import graeme.hosford.eventmanager.data.login.CurrentUserNetworkAccess
import javax.inject.Inject

class JoinCompanyInteractorImpl @Inject constructor(
    private val companyFirebaseAccess: CompanyFirebaseAccess,
    private val currentUserNetworkAccess: CurrentUserNetworkAccess
) : BaseInteractor<JoinCompanyInteractor.JoinCompanyListener>(), JoinCompanyInteractor {

    override fun onCreate() {
        super.onCreate()
        companyFirebaseAccess.setAddUserListener(AddUserListener())
    }

    override fun addCurrentUserToCompany(id: String) {
        companyFirebaseAccess.addUserToCompany(
            id,
            currentUserNetworkAccess.getCurrentUser()?.email!!
        )
    }

    private inner class AddUserListener : CompanyFirebaseAccess.AddUserListener {
        override fun onAddUserSuccess(companyId: String) {
            callback?.onJoinCompanySuccess(companyId)
        }

        override fun onAddUserFailure() {
            callback?.onJoinCompanyFailure()
        }
    }
}