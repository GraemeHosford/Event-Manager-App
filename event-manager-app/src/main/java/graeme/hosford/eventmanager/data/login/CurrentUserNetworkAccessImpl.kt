package graeme.hosford.eventmanager.data.login

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import javax.inject.Inject

const val USERS_COLLECTION = "Users"

class CurrentUserNetworkAccessImpl @Inject constructor() :
    CurrentUserNetworkAccess {

    private var emailSaveListener: CurrentUserNetworkAccess.EmailSaveListener? = null

    private var userCompanyListener: CurrentUserNetworkAccess.AddUserCompanyListener? = null

    override fun setAddUserCompanyListener(listener: CurrentUserNetworkAccess.AddUserCompanyListener) {
        userCompanyListener = listener
    }

    override fun setEmailSaveListener(listener: CurrentUserNetworkAccess.EmailSaveListener) {
        emailSaveListener = listener
    }

    override fun getCurrentUser(): FirebaseUser? {
        return FirebaseAuth.getInstance().currentUser
    }

    override fun saveUserInfo(userEmail: String, isAdmin: Boolean) {
        FirebaseFirestore.getInstance()
            .collection(USERS_COLLECTION)
            .document(userEmail)
            .set(
                hashMapOf(
                    "email" to userEmail,
                    "isAdmin" to isAdmin
                ),
                SetOptions.merge()
            ).addOnSuccessListener {
                emailSaveListener?.onEmailSaveSuccess()
            }.addOnFailureListener {
                emailSaveListener?.onEmailSaveFailure()
            }
    }

    override fun setUserCompany(userEmail: String, companyId: String) {
        FirebaseFirestore.getInstance()
            .collection(USERS_COLLECTION)
            .document(userEmail)
            .set(
                hashMapOf(
                    "companyId" to companyId
                ),
                SetOptions.merge()
            ).addOnSuccessListener {
                userCompanyListener?.onAddUserCompanySuccess()
            }.addOnFailureListener {
                userCompanyListener?.onAddUserCompanyFailure()
            }
    }
}