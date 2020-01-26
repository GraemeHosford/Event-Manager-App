package graeme.hosford.eventmanager.data.login

import com.google.firebase.auth.FirebaseUser

interface CurrentUserNetworkAccess {

    interface EmailSaveListener {
        fun onEmailSaveSuccess()

        fun onEmailSaveFailure()
    }

    interface AddUserCompanyListener {
        fun onAddUserCompanySuccess()

        fun onAddUserCompanyFailure()
    }

    fun setEmailSaveListener(listener: EmailSaveListener)

    fun setAddUserCompanyListener(listener: AddUserCompanyListener)

    fun getCurrentUser(): FirebaseUser?

    fun saveUserInfo(userEmail: String, isAdmin: Boolean = false)

    fun setUserCompany(userEmail: String, companyId: String)

}