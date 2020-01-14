package graeme.hosford.eventmanager.business.login

import graeme.hosford.eventmanager.business.common.BaseInteractor
import graeme.hosford.eventmanager.data.login.CurrentUserNetworkAccess
import javax.inject.Inject

class LoginInteractorImpl @Inject constructor(
    private val currentUserNetworkAccess: CurrentUserNetworkAccess
) : BaseInteractor(), LoginInteractor {

    override fun loggedIn(): Boolean {
        return currentUserNetworkAccess.getCurrentUser() != null
    }
}