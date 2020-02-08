package graeme.hosford.eventmanager.presentation.event.create

import graeme.hosford.eventmanager.presentation.common.presenter.Presenter

interface CreateEventPresenter : Presenter<CreateEventView> {

    fun onChooseAttendeesButtonClick()

    fun onCreateEventButtonClick(
        name: String,
        description: String,
        location: String,
        attendees: ArrayList<String>
    )

    fun getInvitedAttendees(): ArrayList<String>

    fun onChooseStartDateButtonClick()

    fun onChooseEndDateButtonClick()

    fun onChooseStartTimeButtonClick()

    fun onChooseEndTimeButtonClick()

    fun startDateChosen(year: Int, month: Int, dayOfMonth: Int)

    fun endDateChosen(year: Int, month: Int, dayOfMonth: Int)

    fun startTimeChosen(hourofDay: Int, minute: Int)

    fun endTimeChosen(hourofDay: Int, minute: Int)

}