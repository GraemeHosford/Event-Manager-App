package graeme.hosford.eventmanager.presentation.usereventdetail

import graeme.hosford.eventmanager.presentation.common.view.toast.ToastView

interface AddUserEventDetailView : ToastView {
    fun onDetailsSaved()
}