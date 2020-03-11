package graeme.hosford.eventmanager.presentation.attendees.detail

const val ATTENDEE_ID_ARG = "AttendeeID"

interface AttendeesDetailView {
    fun showAttendeeOptions(id: String)
}