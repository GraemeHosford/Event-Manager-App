package graeme.hosford.eventmanager.presentation.feedback.presentation

import graeme.hosford.eventmanager.business.feedback.FeedbackInteractor
import graeme.hosford.eventmanager.presentation.common.presenter.BasePresenter
import graeme.hosford.eventmanager.presentation.feedback.FeedbackPresenter
import graeme.hosford.eventmanager.presentation.feedback.FeedbackView
import javax.inject.Inject

class FeedbackPresenterImpl @Inject constructor(
    private val interactor: FeedbackInteractor
) : BasePresenter<FeedbackView, FeedbackInteractor>(interactor), FeedbackPresenter {

    override fun onSubmitFeedbackButtonClick(
        eventCreationRating: Float,
        eventManagementRating: Float,
        attendeeManagementRating: Float,
        otherComments: String
    ) {
        interactor.saveFeedback(
            eventCreationRating,
            eventManagementRating,
            attendeeManagementRating,
            otherComments
        )
    }
}