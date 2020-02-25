package graeme.hosford.eventmanager.presentation.attendees

import graeme.hosford.eventmanager.presentation.attendees.model.AttendeesUiModel
import graeme.hosford.eventmanager.presentation.common.view.recyclerview.RecyclerViewListView
import graeme.hosford.eventmanager.presentation.common.view.toast.ToastView

interface AttendeesView : RecyclerViewListView<AttendeesUiModel>, ToastView {

    companion object {
        const val ATTENDEE_EMAILS_KEY = "ATTENDEES_EMAILS"
    }

    fun returnAttendeeEmails()

}