package graeme.hosford.eventmanager.presentation.feedback

import graeme.hosford.eventmanager.presentation.common.presenter.Presenter

interface FeedbackPresenter : Presenter<FeedbackView> {

    fun onSubmitFeedbackButtonClick(
        eventCreationRating: Float,
        eventManagementRating: Float,
        attendeeManagementRating: Float,
        otherComments: String
    )

}