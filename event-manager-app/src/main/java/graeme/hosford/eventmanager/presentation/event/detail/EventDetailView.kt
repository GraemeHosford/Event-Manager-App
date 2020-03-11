package graeme.hosford.eventmanager.presentation.event.detail

import graeme.hosford.eventmanager.presentation.common.model.SingleUiModelView
import graeme.hosford.eventmanager.presentation.common.view.toast.ToastView
import graeme.hosford.eventmanager.presentation.event.detail.model.EventDetailUiModel

const val ATTENDEES_ARG = "Attendees"

interface EventDetailView : ToastView, SingleUiModelView<EventDetailUiModel>