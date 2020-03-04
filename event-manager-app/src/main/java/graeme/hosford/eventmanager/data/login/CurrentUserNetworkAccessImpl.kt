package graeme.hosford.eventmanager.data.login

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import graeme.hosford.eventmanager.data.login.converter.PersonEntityConverter
import javax.inject.Inject

const val USERS_COLLECTION = "Users"

class CurrentUserNetworkAccessImpl @Inject constructor(
    private val personConverter: PersonEntityConverter
) : CurrentUserNetworkAccess {

    lateinit var userInfoSavedCallback: CurrentUserNetworkAccess.UserInfoSavedCallback
    lateinit var userInfoRetrievedCallback: CurrentUserNetworkAccess.UserInfoRetrievedCallback

    override fun setUserInfoSavedListener(userInfoListener: CurrentUserNetworkAccess.UserInfoSavedCallback) {
        userInfoSavedCallback = userInfoListener
    }

    override fun setUserInfoRetrievedListener(
        userInfoRetrievedCallback: CurrentUserNetworkAccess.UserInfoRetrievedCallback
    ) {
        this.userInfoRetrievedCallback = userInfoRetrievedCallback
    }

    override fun getCurrentUser(): FirebaseUser? {
        return FirebaseAuth.getInstance().currentUser
    }

    override fun getUserInfo(userEmail: String, details: String) {
        FirebaseFirestore.getInstance()
            .collection(USERS_COLLECTION)
            .document(userEmail)
            .get()
            .addOnSuccessListener {
                val person = personConverter.convert(it)
                userInfoRetrievedCallback.onUserInfoRetrieved(person)
            }.addOnFailureListener {
                userInfoRetrievedCallback.onUserInfoRetrievalFailure()
            }
    }

    override fun getFullUserInfo(userEmail: String) {
        FirebaseFirestore.getInstance()
            .collection(USERS_COLLECTION)
            .document(userEmail)
            .get()
            .addOnSuccessListener {
                val person = personConverter.convert(it)
                userInfoRetrievedCallback.onUserInfoRetrieved(person)
            }.addOnFailureListener {
                userInfoRetrievedCallback.onUserInfoRetrievalFailure()
            }
    }

    override fun saveUserInfo(userEmail: String, details: HashMap<String, Any>) {
        FirebaseFirestore.getInstance()
            .collection(USERS_COLLECTION)
            .document(userEmail)
            .set(
                details,
                SetOptions.merge()
            ).addOnSuccessListener {
                userInfoSavedCallback.onUserInfoSavedSuccess()
            }.addOnFailureListener {
                userInfoSavedCallback.onUserInfoSavedFailure()
            }
    }
}