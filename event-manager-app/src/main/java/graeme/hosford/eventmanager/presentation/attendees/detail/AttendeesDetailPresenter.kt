package graeme.hosford.eventmanager.presentation.attendees.detail

import graeme.hosford.eventmanager.presentation.common.presenter.Presenter

interface AttendeesDetailPresenter : Presenter<AttendeesDetailView> {
    fun showAttendees(attendees: ArrayList<String>)

    fun onAttendeeClick(id: String)
}