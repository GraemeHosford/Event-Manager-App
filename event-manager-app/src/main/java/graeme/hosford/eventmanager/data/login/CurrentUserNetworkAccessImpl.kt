package graeme.hosford.eventmanager.data.login

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class CurrentUserNetworkAccessImpl @Inject constructor() :
    CurrentUserNetworkAccess {

    override fun getCurrentUser(): FirebaseUser? {
        return FirebaseAuth.getInstance().currentUser
    }
}