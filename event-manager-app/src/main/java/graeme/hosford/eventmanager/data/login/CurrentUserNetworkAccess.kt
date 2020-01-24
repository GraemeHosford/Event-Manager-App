package graeme.hosford.eventmanager.data.login

import com.google.firebase.auth.FirebaseUser

interface CurrentUserNetworkAccess {

    interface EmailSaveListener {
        fun onEmailSaveSuccess()

        fun onEmailSaveFailure()
    }

    fun setEmailSaveListener(listener: EmailSaveListener)

    fun getCurrentUser(): FirebaseUser?

    fun saveUserInfo(userEmail: String, isAdmin: Boolean = false)

}