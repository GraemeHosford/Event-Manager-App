package graeme.hosford.eventmanager.data.feedback

import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class FeedbackFirebaseAccessImpl @Inject constructor() : FeedbackFirebaseAccess {

    override fun saveFeedback(feedback: HashMap<String, Any>) {
        FirebaseFirestore.getInstance()
            .collection(FEEDBACK_COLLECTION)
            .add(feedback)
    }
}