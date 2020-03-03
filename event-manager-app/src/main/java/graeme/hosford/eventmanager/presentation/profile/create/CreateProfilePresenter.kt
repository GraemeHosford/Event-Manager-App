package graeme.hosford.eventmanager.presentation.profile.create

import graeme.hosford.eventmanager.presentation.common.presenter.Presenter

interface CreateProfilePresenter : Presenter<CreateProfileView> {
    fun onCreateProfileButtonClick()
}