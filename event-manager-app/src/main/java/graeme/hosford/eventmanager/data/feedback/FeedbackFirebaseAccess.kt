package graeme.hosford.eventmanager.data.feedback

const val FEEDBACK_COLLECTION = "Feedback"

interface FeedbackFirebaseAccess {

    fun saveFeedback(feedback: HashMap<String, Any>)

}