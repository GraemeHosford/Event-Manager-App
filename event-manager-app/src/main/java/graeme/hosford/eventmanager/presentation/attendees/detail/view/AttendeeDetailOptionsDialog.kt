package graeme.hosford.eventmanager.presentation.attendees.detail.view

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import graeme.hosford.eventmanager.presentation.attendees.detail.ATTENDEE_ID_ARG
import graeme.hosford.eventmanager.presentation.attendees.detail.view.adapter.AttendeeDialogOptionsPresenterBridge

class AttendeeDetailOptionsDialog : DialogFragment() {

    lateinit var listener: AttendeeDialogOptionsPresenterBridge

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return parentFragment?.let {
            val userId = arguments?.getString(ATTENDEE_ID_ARG)
                ?: throw IllegalArgumentException("There must be an id for a chosen attendee")

            val builder = AlertDialog.Builder(it.requireActivity())

            builder.setItems(
                arrayOf("View Profile", "Add Event Details")
            ) { dialog, which ->
                when (which) {
                    0 -> listener.onViewProfileClick(userId)
                    1 -> listener.onAddEventDetailClick(userId)
                }

                dialog.cancel()
            }

            builder.create()
        } ?: throw IllegalStateException("Parent fragment cannot be null")
    }
}