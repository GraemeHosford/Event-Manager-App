package graeme.hosford.eventmanager.presentation.attendees.detail

import graeme.hosford.eventmanager.presentation.common.presenter.Presenter

interface AttendeesDetailPresenter : Presenter<AttendeesDetailView> {
    fun getAttendees(eventId: String)

    fun onAttendeeClick(id: String)
}