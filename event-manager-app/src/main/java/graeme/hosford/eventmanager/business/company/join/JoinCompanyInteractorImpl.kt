package graeme.hosford.eventmanager.business.company.join

import graeme.hosford.eventmanager.business.common.BaseInteractor
import graeme.hosford.eventmanager.business.common.Interactor
import graeme.hosford.eventmanager.business.user.CurrentUserInteractor
import graeme.hosford.eventmanager.data.company.CompanyFirebaseAccess
import graeme.hosford.eventmanager.data.login.CurrentUserNetworkAccess
import javax.inject.Inject

class JoinCompanyInteractorImpl @Inject constructor(
    private val companyFirebaseAccess: CompanyFirebaseAccess,
    private val currentUserNetworkAccess: CurrentUserNetworkAccess,
    private val currentUserInteractor: CurrentUserInteractor
) : BaseInteractor<JoinCompanyInteractor.JoinCompanyListener>(), JoinCompanyInteractor {

    override fun onCreate() {
        super.onCreate()
        companyFirebaseAccess.setAddUserListener(AddUserListener())
    }

    override fun registerManagedInteractors(): List<Interactor<*>> = listOf(currentUserInteractor)

    override fun registerCurrentUserInteractorCallback(listener: CurrentUserInteractor.UserCompanyListener) {
        currentUserInteractor.registerCallback(listener)
    }

    override fun setUserCompany(companyId: String) {
        currentUserInteractor.setUserCompany(companyId)
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