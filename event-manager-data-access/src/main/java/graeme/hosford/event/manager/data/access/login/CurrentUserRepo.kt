package graeme.hosford.event.manager.data.access.login

import com.google.firebase.auth.FirebaseUser
import graeme.hosford.event.manager.data.access.DataRepo

interface CurrentUserRepo: DataRepo {

    fun getCurrentUser(): FirebaseUser?

}