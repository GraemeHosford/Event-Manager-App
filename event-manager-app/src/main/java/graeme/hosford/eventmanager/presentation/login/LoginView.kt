package graeme.hosford.eventmanager.presentation.login

const val SIGN_IN_REQUEST_CODE = 101

interface LoginView {

    fun showLoginFlow()

    fun showCompanyCreationFlow()

}