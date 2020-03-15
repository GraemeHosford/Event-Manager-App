package graeme.hosford.eventmanager.presentation.usereventdetail.create

import graeme.hosford.eventmanager.presentation.common.presenter.Presenter

interface AddUserEventDetailPresenter : Presenter<AddUserEventDetailView> {
    fun onConfirmDetailsButtonClick(
        userId: String,
        eventId: String,
        eventName: String,
        subject: String,
        details: String
    )
}