package graeme.hosford.eventmanager.presentation.event.create

import graeme.hosford.eventmanager.presentation.common.view.toast.ToastView

interface CreateEventView : ToastView {

    companion object {
        const val CHOOSE_ATTENDEES_REQUEST_CODE = 1233
    }

    fun showChooseAttendeesFragment()

}