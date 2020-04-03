package graeme.hosford.eventmanager.business.feedback

import graeme.hosford.eventmanager.business.common.Interactor

interface FeedbackInteractor : Interactor<FeedbackInteractor.FeedbackInteractorCallback> {

    interface FeedbackInteractorCallback

    fun saveFeedback(
        eventCreationRating: Float,
        eventManagementRating: Float,
        attendeeRating: Float,
        otherComments: String
    )

}