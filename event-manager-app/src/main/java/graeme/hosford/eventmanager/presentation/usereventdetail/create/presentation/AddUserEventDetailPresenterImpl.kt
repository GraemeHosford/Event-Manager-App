package graeme.hosford.eventmanager.presentation.usereventdetail.create.presentation

import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.business.usereventdetail.UserEventDetailInteractor
import graeme.hosford.eventmanager.presentation.common.presenter.BasePresenter
import graeme.hosford.eventmanager.presentation.usereventdetail.create.AddUserEventDetailPresenter
import graeme.hosford.eventmanager.presentation.usereventdetail.create.AddUserEventDetailView
import javax.inject.Inject

class AddUserEventDetailPresenterImpl @Inject constructor(
    private val interactor: UserEventDetailInteractor
) : BasePresenter<AddUserEventDetailView, UserEventDetailInteractor>(interactor),
    AddUserEventDetailPresenter {

    override fun onViewCreated(view: AddUserEventDetailView) {
        super.onViewCreated(view)
        interactor.registerCallback(AddDetailInteractorCallback())
    }

    override fun onConfirmDetailsButtonClick(
        userId: String,
        eventId: String,
        subject: String,
        details: String
    ) {
        if (details.isBlank() || subject.isBlank()) {
            view?.showLongToast("Enter a subject and some details to save for this user")
        } else {
            interactor.saveEventDetail(userId, eventId, subject, details)
        }
    }

    private inner class AddDetailInteractorCallback :
        UserEventDetailInteractor.UserEventDetailInteractorCallback {
        override fun onDetailsSaved() {
            view?.showLongToast("Details Saved Successfully")
            view?.onDetailsSaved()
        }

        override fun onDetailsSaveFailed() {
            view?.showLongToast(R.string.generic_error_saving_data)
        }
    }
}