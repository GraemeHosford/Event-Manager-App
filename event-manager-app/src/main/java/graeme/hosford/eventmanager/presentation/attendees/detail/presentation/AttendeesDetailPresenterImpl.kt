package graeme.hosford.eventmanager.presentation.attendees.detail.presentation

import graeme.hosford.eventmanager.business.attendees.detail.AttendeesDetailInteractor
import graeme.hosford.eventmanager.presentation.attendees.detail.AttendeesDetailPresenter
import graeme.hosford.eventmanager.presentation.attendees.detail.AttendeesDetailView
import graeme.hosford.eventmanager.presentation.common.presenter.BasePresenter
import javax.inject.Inject

class AttendeesDetailPresenterImpl @Inject constructor(
    private val interactor: AttendeesDetailInteractor
) : BasePresenter<AttendeesDetailView, AttendeesDetailInteractor>(interactor),
    AttendeesDetailPresenter {

    override fun getAttendees(eventId: String) {
        interactor.getEventAttendees(eventId)
    }

    override fun onAttendeeClick(id: String) {
        view?.showAttendeeOptions(id)
    }
}