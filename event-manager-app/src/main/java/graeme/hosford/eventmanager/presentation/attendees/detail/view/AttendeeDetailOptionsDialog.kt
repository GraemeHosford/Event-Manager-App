package graeme.hosford.eventmanager.presentation.attendees.detail.view

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import graeme.hosford.eventmanager.presentation.attendees.detail.ATTENDEE_ID_ARG
import graeme.hosford.eventmanager.presentation.attendees.detail.view.adapter.AttendeeDialogOptionsPresenterBridge
import graeme.hosford.eventmanager.presentation.event.detail.EVENT_ID_ARG

class AttendeeDetailOptionsDialog : DialogFragment() {

    lateinit var listener: AttendeeDialogOptionsPresenterBridge

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return parentFragment?.let {
            val userId = arguments?.getString(ATTENDEE_ID_ARG)
                ?: throw IllegalArgumentException("AttendeeDetailOptionsDialog: AttendeeId cannot be null")

            val eventId = arguments?.getString(EVENT_ID_ARG)
                ?: throw IllegalArgumentException("AttendeeDetailOptionsDialog: EventId cannot be null")

            val builder = AlertDialog.Builder(it.requireActivity())

            builder.setItems(
                arrayOf("View Profile", "Add Event Details")
            ) { dialog, which ->
                when (which) {
                    0 -> listener.onViewProfileClick(userId)
                    1 -> listener.onAddEventDetailClick(userId, eventId)
                }

                dialog.cancel()
            }

            builder.create()
        } ?: throw IllegalStateException("Parent fragment cannot be null")
    }
}