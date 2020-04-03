package graeme.hosford.eventmanager.business.feedback

import graeme.hosford.eventmanager.business.common.BaseInteractor
import graeme.hosford.eventmanager.data.feedback.FeedbackFirebaseAccess
import javax.inject.Inject

class FeedbackInteractorImpl @Inject constructor(
    private val feedbackFirebaseAccess: FeedbackFirebaseAccess
) : BaseInteractor<FeedbackInteractor.FeedbackInteractorCallback>(), FeedbackInteractor {

    override fun saveFeedback(
        eventCreationRating: Float,
        eventManagementRating: Float,
        attendeeRating: Float,
        otherComments: String
    ) {
        feedbackFirebaseAccess.saveFeedback(
            hashMapOf(
                "event_creation_rating" to eventCreationRating,
                "event_management_rating" to eventManagementRating,
                "attendee_rating" to attendeeRating,
                "other_comments" to otherComments
            )
        )
    }
}