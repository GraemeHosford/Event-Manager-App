package graeme.hosford.eventmanager.presentation.profile.create.presentation

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import graeme.hosford.eventmanager.business.profile.create.CreateProfileInteractor
import graeme.hosford.eventmanager.presentation.common.presenter.BasePresenter
import graeme.hosford.eventmanager.presentation.profile.create.CAMERA_REQUEST_CODE
import graeme.hosford.eventmanager.presentation.profile.create.CreateProfilePresenter
import graeme.hosford.eventmanager.presentation.profile.create.CreateProfileView
import javax.inject.Inject

class CreateProfilePresenterImpl @Inject constructor(
    private val interactor: CreateProfileInteractor
) : BasePresenter<CreateProfileView, CreateProfileInteractor>(interactor),
    CreateProfilePresenter {

    override fun onProfileImageClick() {
        view?.startCamera()
    }

    override fun onCreateProfileButtonClick(
        firstName: String,
        lastName: String,
        jobTitle: String,
        description: String
    ) {

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
            val image = data?.extras?.get("data") as Bitmap
            view?.setProfileImage(image)
        }
    }
}