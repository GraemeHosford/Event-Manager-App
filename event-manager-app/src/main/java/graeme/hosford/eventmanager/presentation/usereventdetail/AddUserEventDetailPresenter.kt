package graeme.hosford.eventmanager.presentation.usereventdetail

import graeme.hosford.eventmanager.presentation.common.presenter.Presenter

interface AddUserEventDetailPresenter : Presenter<AddUserEventDetailView> {
    fun onConfirmDetailsButtonClick(
        userId: String,
        eventId: String,
        subject: String,
        details: String
    )
}