package graeme.hosford.eventmanager.data.login

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.storage.FirebaseStorage
import graeme.hosford.eventmanager.data.login.converter.PersonEntityConverter
import javax.inject.Inject

const val USERS_COLLECTION = "Users"
const val USER_PROFILE_IMAGE_PATH = "user_profile/"

class CurrentUserNetworkAccessImpl @Inject constructor(
    private val personConverter: PersonEntityConverter
) : CurrentUserNetworkAccess {

    private lateinit var userInfoSavedCallback: CurrentUserNetworkAccess.UserInfoSavedCallback
    private lateinit var userInfoRetrievedCallback: CurrentUserNetworkAccess.UserInfoRetrievedCallback
    private lateinit var profileImageUploadedCallback: CurrentUserNetworkAccess.ProfileImageUploadedCallback

    override fun setUserInfoSavedListener(userInfoListener: CurrentUserNetworkAccess.UserInfoSavedCallback) {
        userInfoSavedCallback = userInfoListener
    }

    override fun setUserInfoRetrievedListener(
        userInfoRetrievedCallback: CurrentUserNetworkAccess.UserInfoRetrievedCallback
    ) {
        this.userInfoRetrievedCallback = userInfoRetrievedCallback
    }

    override fun setProfileImageUploadedCallback(callback: CurrentUserNetworkAccess.ProfileImageUploadedCallback) {
        profileImageUploadedCallback = callback
    }

    override fun getCurrentUser(): FirebaseUser? {
        return FirebaseAuth.getInstance().currentUser
    }

    override fun saveUserProfileImage(imageBytes: ByteArray) {
        val path = "${USER_PROFILE_IMAGE_PATH}${getCurrentUser()!!.email}"

        FirebaseStorage.getInstance()
            .getReference(path)
            .putBytes(imageBytes)
            .addOnSuccessListener {
                profileImageUploadedCallback.onProfileImageUploadedSuccessfully(path)
            }.addOnFailureListener {
                profileImageUploadedCallback.onProfileImageUploadFailed()
            }
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