package graeme.hosford.eventmanager.presentation.profile.detail

import graeme.hosford.eventmanager.presentation.common.presenter.Presenter

interface ProfileDetailPresenter : Presenter<ProfileDetailView> {

    fun getPersonDetail(personId: String)

}