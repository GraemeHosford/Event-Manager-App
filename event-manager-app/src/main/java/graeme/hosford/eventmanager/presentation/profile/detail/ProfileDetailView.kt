package graeme.hosford.eventmanager.presentation.profile.detail

import graeme.hosford.eventmanager.presentation.common.model.SingleUiModelView
import graeme.hosford.eventmanager.presentation.common.view.toast.ToastView
import graeme.hosford.eventmanager.presentation.profile.detail.model.ProfileDetailUiModel

interface ProfileDetailView : ToastView, SingleUiModelView<ProfileDetailUiModel>