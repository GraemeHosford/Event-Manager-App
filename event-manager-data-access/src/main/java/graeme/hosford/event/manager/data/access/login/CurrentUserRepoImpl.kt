package graeme.hosford.event.manager.data.access.login

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class CurrentUserRepoImpl @Inject constructor(
): CurrentUserRepo {

    override fun getCurrentUser(): FirebaseUser? {
        return FirebaseAuth.getInstance().currentUser
    }
}