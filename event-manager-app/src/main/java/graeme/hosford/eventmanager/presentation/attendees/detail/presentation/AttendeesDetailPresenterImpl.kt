package graeme.hosford.eventmanager.presentation.attendees.detail.presentation

import graeme.hosford.eventmanager.business.attendees.AttendeesInteractor
import graeme.hosford.eventmanager.presentation.attendees.detail.AttendeesDetailPresenter
import graeme.hosford.eventmanager.presentation.attendees.detail.AttendeesDetailView
import graeme.hosford.eventmanager.presentation.common.presenter.BasePresenter
import javax.inject.Inject

class AttendeesDetailPresenterImpl @Inject constructor(
    private val interactor: AttendeesInteractor
) : BasePresenter<AttendeesDetailView, AttendeesInteractor>(interactor), AttendeesDetailPresenter {

    override fun onAttendeeClick(id: String) {
        view?.showAttendeeOptions(id)
    }
}