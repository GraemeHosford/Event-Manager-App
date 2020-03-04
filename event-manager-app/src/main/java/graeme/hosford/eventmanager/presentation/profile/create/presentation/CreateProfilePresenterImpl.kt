package graeme.hosford.eventmanager.presentation.profile.create.presentation

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.business.profile.create.CreateProfileInteractor
import graeme.hosford.eventmanager.business.user.CurrentUserInteractor
import graeme.hosford.eventmanager.entity.company.Person
import graeme.hosford.eventmanager.presentation.common.presenter.BasePresenter
import graeme.hosford.eventmanager.presentation.profile.create.CAMERA_REQUEST_CODE
import graeme.hosford.eventmanager.presentation.profile.create.CreateProfilePresenter
import graeme.hosford.eventmanager.presentation.profile.create.CreateProfileView
import javax.inject.Inject

class CreateProfilePresenterImpl @Inject constructor(
    private val interactor: CreateProfileInteractor,
    private val currentUserInteractor: CurrentUserInteractor
) : BasePresenter<CreateProfileView, CreateProfileInteractor>(interactor),
    CreateProfilePresenter {

    override fun onViewCreated(view: CreateProfileView) {
        super.onViewCreated(view)
        interactor.registerCallback(ProfileInteractorCallback())
        currentUserInteractor.registerCallback(UserInteractorInfoListener())
    }

    override fun onProfileImageClick() {
        view?.startCamera()
    }

    override fun onCreateProfileButtonClick(
        firstName: String,
        lastName: String,
        jobTitle: String,
        description: String
    ) {
        if (firstName.isNotBlank() && lastName.isNotBlank()) {
            interactor.saveUserProfileData(firstName, lastName, jobTitle, description)
        } else {
            view?.showLongToast(R.string.create_profile_error_enter_name)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
            val image = data?.extras?.get("data") as Bitmap
            view?.setProfileImage(image)
        }
    }

    private inner class ProfileInteractorCallback : CreateProfileInteractor.CreateProfileCallback {
        override fun onUserInfoSavedSuccessfully() {
            currentUserInteractor.checkUserHasCompany()
        }

        override fun onUserInfoSaveFailed() {
            view?.showLongToast(R.string.generic_error_saving_data)
        }

        override fun onProfileInfoRetrieved(data: Person) {
            if (data.companyId == null) {
                view?.showCompanyCreationFlow()
            } else {
                view?.showMainActivity()
            }
        }

        override fun onProfileInfoNotRetrieved() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

    private inner class UserInteractorInfoListener : CurrentUserInteractor.UserCompanyListener {
        override fun onAddUserCompanySuccess() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onAddUserCompanyFailure() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onUserInfoRetrieved(info: Any?) {
            view?.showMainActivity()
        }

        override fun onUserInfoRetrievalFailed() {
            view?.showCompanyCreationFlow()
        }
    }
}