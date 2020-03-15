package graeme.hosford.eventmanager.presentation.usereventdetail.detail

import graeme.hosford.eventmanager.presentation.common.view.recyclerview.RecyclerViewListView
import graeme.hosford.eventmanager.presentation.common.view.toast.ToastView
import graeme.hosford.eventmanager.presentation.usereventdetail.detail.model.UserEventDetailListUiModel

interface UserEventDetailView : ToastView, RecyclerViewListView<UserEventDetailListUiModel> {
}