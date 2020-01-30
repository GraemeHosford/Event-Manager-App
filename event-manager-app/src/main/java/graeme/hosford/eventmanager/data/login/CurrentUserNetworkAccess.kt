package graeme.hosford.eventmanager.data.login

import com.google.firebase.auth.FirebaseUser

interface CurrentUserNetworkAccess {

    interface UserInfoSavedCallback {
        fun onUserInfoSavedSuccess()

        fun onUserInfoSavedFailure()
    }

    interface UserInfoRetrievedCallback {
        fun onUserInfoRetrieved(info: Any?)

        fun onUserInfoRetrievalFailure()
    }

    fun setUserInfoSavedListener(userInfoListener: UserInfoSavedCallback)

    fun setUserInfoRetrievedListener(userInfoRetrievedCallback: UserInfoRetrievedCallback)

    fun getCurrentUser(): FirebaseUser?

    fun saveUserInfo(userEmail: String, details: HashMap<String, Any>)

    fun getUserInfo(userEmail: String, details: String)

}