package graeme.hosford.eventmanager.presentation.usereventdetail.detail

import graeme.hosford.eventmanager.presentation.common.presenter.Presenter

interface UserEventDetailPresenter : Presenter<UserEventDetailView> {
    fun getUserEventDetails(userId: String)
}