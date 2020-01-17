package graeme.hosford.eventmanager.data.login

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

const val USERS_COLLECTION = "Users"

class CurrentUserNetworkAccessImpl @Inject constructor() :
    CurrentUserNetworkAccess {

    private var emailSaveListener: CurrentUserNetworkAccess.EmailSaveListener? = null

    override fun setEmailSaveListener(listener: CurrentUserNetworkAccess.EmailSaveListener) {
        emailSaveListener = listener
    }

    override fun getCurrentUser(): FirebaseUser? {
        return FirebaseAuth.getInstance().currentUser
    }

    override fun saveUserInfo(userEmail: String) {
        FirebaseFirestore.getInstance()
            .collection(USERS_COLLECTION)
            .add(
                hashMapOf(
                    "email" to userEmail
                )
            ).addOnSuccessListener {
                emailSaveListener?.onEmailSaveSuccess()
            }.addOnFailureListener {
                emailSaveListener?.onEmailSaveFailure()
            }
    }
}