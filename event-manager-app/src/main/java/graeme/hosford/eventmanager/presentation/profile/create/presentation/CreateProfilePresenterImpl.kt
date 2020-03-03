package graeme.hosford.eventmanager.presentation.profile.create.presentation

import graeme.hosford.eventmanager.business.profile.create.CreateProfileInteractor
import graeme.hosford.eventmanager.presentation.common.presenter.BasePresenter
import graeme.hosford.eventmanager.presentation.profile.create.CreateProfilePresenter
import graeme.hosford.eventmanager.presentation.profile.create.CreateProfileView
import javax.inject.Inject

class CreateProfilePresenterImpl @Inject constructor(
    private val interactor: CreateProfileInteractor
) : BasePresenter<CreateProfileView, CreateProfileInteractor>(interactor),
    CreateProfilePresenter {

    override fun onCreateProfileButtonClick() {
        
    }
}