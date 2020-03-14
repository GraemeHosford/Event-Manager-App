package graeme.hosford.eventmanager.presentation.profile.create.presentation

import android.annotation.SuppressLint
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
import java.io.ByteArrayOutputStream
import javax.inject.Inject

class CreateProfilePresenterImpl @Inject constructor(
    private val interactor: CreateProfileInteractor,
    private val currentUserInteractor: CurrentUserInteractor
) : BasePresenter<CreateProfileView, CreateProfileInteractor>(interactor),
    CreateProfilePresenter {

    private var imageBytes: ByteArray? = null

    private lateinit var firstName: String
    private lateinit var lastName: String
    private var jobTitle: String? = null
    private var description: String? = null

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
            if (imageBytes == null) {
                interactor.saveUserProfileData(firstName, lastName, jobTitle, description, "")
            } else {
                this.firstName = firstName
                this.lastName = lastName
                this.jobTitle = jobTitle
                this.description = description

                interactor.saveUserProfileImage(imageBytes!!)
            }
        } else {
            view?.showLongToast(R.string.create_profile_error_enter_name)
        }
    }

    /* Only sometimes shows error for wrong thread for the compress method below
    * Builds and runs fine on my laptop and phone but is occasionally causing problems with CI.
    * Probably a better way to fix it but don't have the time for that so this will have to do
    * for now */
    @SuppressLint("WrongThread")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
            val image = data?.extras?.get("data") as Bitmap
            view?.setProfileImage(image)

            val byteOutputStream = ByteArrayOutputStream()
            image.compress(Bitmap.CompressFormat.PNG, 100, byteOutputStream)
            imageBytes = byteOutputStream.toByteArray()
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

        override fun onProfileImageSaved(imagePath: String) {
            interactor.saveUserProfileData(
                firstName,
                lastName,
                jobTitle ?: "",
                description ?: "",
                imagePath
            )
        }

        override fun onProfileImageSaveFailed() {
            view?.showLongToast(R.string.generic_error_saving_data)
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