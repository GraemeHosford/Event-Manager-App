package graeme.hosford.eventmanager.presentation.event.detail

import graeme.hosford.eventmanager.presentation.common.model.SingleUiModelView
import graeme.hosford.eventmanager.presentation.common.view.toast.ToastView
import graeme.hosford.eventmanager.presentation.event.detail.model.EventDetailUiModel

const val ATTENDEES_ARG = "Attendees"
const val EVENT_ID_ARG = "EventID"
const val COMPANY_ID_ARG = "ComapnyID"

interface EventDetailView : ToastView, SingleUiModelView<EventDetailUiModel>