package graeme.hosford.eventmanager.presentation.login

import graeme.hosford.eventmanager.presentation.common.toast.ToastView

const val SIGN_IN_REQUEST_CODE = 101

interface LoginView : ToastView {

    fun showLoginFlow()

    fun showCompanyCreationFlow()

}