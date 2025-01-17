package graeme.hosford.eventmanager.presentation.attendees.choose

import graeme.hosford.eventmanager.presentation.attendees.choose.AttendeesView
import graeme.hosford.eventmanager.presentation.common.presenter.Presenter

interface AttendeesPresenter : Presenter<AttendeesView> {
    fun onAttendeeClick(email: String)

    fun onConfirmAttendeesButtonClick()

    fun getCompanyMembers()

    fun getAttendees(): ArrayList<String>
}