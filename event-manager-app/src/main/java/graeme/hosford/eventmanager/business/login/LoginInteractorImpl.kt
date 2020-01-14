package graeme.hosford.eventmanager.business.login

import javax.inject.Inject

class LoginInteractorImpl @Inject constructor(

) : LoginInteractor {

    override fun loggedIn(): Boolean {
        return true
    }
}