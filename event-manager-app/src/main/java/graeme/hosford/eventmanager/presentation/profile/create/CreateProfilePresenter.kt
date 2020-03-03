package graeme.hosford.eventmanager.presentation.profile.create

import graeme.hosford.eventmanager.presentation.common.presenter.Presenter

interface CreateProfilePresenter : Presenter<CreateProfileView> {
    fun onProfileImageClick()

    fun onCreateProfileButtonClick(
        firstName: String,
        lastName: String,
        jobTitle: String,
        description: String
    )
}