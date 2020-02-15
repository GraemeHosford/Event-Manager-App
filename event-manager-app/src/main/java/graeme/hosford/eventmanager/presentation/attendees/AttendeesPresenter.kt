package graeme.hosford.eventmanager.presentation.attendees

import graeme.hosford.eventmanager.presentation.common.presenter.Presenter

interface AttendeesPresenter : Presenter<AttendeesView> {
    fun onAttendeeClick(email: String)

    fun onConfirmAttendeesButtonClick()

    fun getAttendees(): ArrayList<String>
}