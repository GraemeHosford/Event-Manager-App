package graeme.hosford.eventmanager.presentation.attendees.detail.view.adapter

interface AttendeeDialogOptionsPresenterBridge {
    fun onViewProfileClick(userId: String)

    fun onAddEventDetailClick(userId: String, eventId: String)
}