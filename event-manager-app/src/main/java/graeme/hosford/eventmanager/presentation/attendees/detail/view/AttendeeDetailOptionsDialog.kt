package graeme.hosford.eventmanager.presentation.attendees.detail.view

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import graeme.hosford.eventmanager.presentation.attendees.detail.view.adapter.AttendeeDialogOptionsPresenterBridge
import graeme.hosford.eventmanager.presentation.company.detail.MEMBER_ID_ARG

class AttendeeDetailOptionsDialog : DialogFragment() {

    lateinit var listener: AttendeeDialogOptionsPresenterBridge

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return parentFragment?.let {
            val userId = arguments?.getString(MEMBER_ID_ARG)
                ?: throw IllegalArgumentException("There must be an id for a chosen attendee")

            val builder = AlertDialog.Builder(it.requireActivity())

            builder.setItems(
                arrayOf("View Profile", "Add Event Details")
            ) { _, which ->
                when (which) {
                    0 -> listener.onViewProfileClick(userId)
                    1 -> listener.onAddEventDetailClick(userId)
                }
            }

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}