package graeme.hosford.eventmanager.data.usereventdetail

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import graeme.hosford.eventmanager.data.login.USERS_COLLECTION
import javax.inject.Inject

class UserEventDetailFirebaseAccessImpl @Inject constructor() : UserEventDetailFirebaseAccess {

    override var callback: UserEventDetailFirebaseAccess.UserEventDetailsFirebaseAccessCallback? =
        null

    override fun saveEventDetails(
        userId: String,
        eventId: String,
        subject: String,
        details: String
    ) {
        FirebaseFirestore.getInstance()
            .collection(USERS_COLLECTION)
            .document(userId)
            .collection(EVENT_DETAILS_COLLECTION)
            .document(eventId)
            .set(
                hashMapOf(
                    SUBJECT_KEY to subject,
                    DETAILS_KEY to details
                ),
                SetOptions.merge()
            ).addOnSuccessListener {
                callback?.onDetailsSaved()
            }.addOnFailureListener {
                callback?.onDetailsSaveFailed()
            }
    }
}