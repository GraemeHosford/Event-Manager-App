package graeme.hosford.eventmanager.presentation.attendees.detail

import graeme.hosford.eventmanager.presentation.attendees.choose.model.AttendeesUiModel
import graeme.hosford.eventmanager.presentation.common.view.recyclerview.RecyclerViewListView
import graeme.hosford.eventmanager.presentation.common.view.toast.ToastView

const val ATTENDEE_ID_ARG = "AttendeeID"

interface AttendeesDetailView : RecyclerViewListView<AttendeesUiModel>, ToastView {
    fun showAttendeeOptions(userId: String, eventId: String)
}