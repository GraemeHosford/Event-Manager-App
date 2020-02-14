package graeme.hosford.eventmanager.presentation.event.detail

import graeme.hosford.eventmanager.presentation.common.view.toast.ToastView
import graeme.hosford.eventmanager.presentation.event.detail.model.EventDetailUiModel

interface EventDetailView : ToastView {
    fun setData(model: EventDetailUiModel)
}