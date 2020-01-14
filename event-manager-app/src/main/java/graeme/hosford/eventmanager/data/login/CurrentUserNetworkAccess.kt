package graeme.hosford.eventmanager.data.login

import com.google.firebase.auth.FirebaseUser

interface CurrentUserNetworkAccess {

    fun getCurrentUser(): FirebaseUser?

}