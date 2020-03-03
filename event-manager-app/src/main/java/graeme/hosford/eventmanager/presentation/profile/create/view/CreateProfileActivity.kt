package graeme.hosford.eventmanager.presentation.profile.create.view

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import graeme.hosford.eventmanager.EventManagerApplication
import graeme.hosford.eventmanager.databinding.ActivityCreateProfileBinding
import graeme.hosford.eventmanager.presentation.CoreIntents
import graeme.hosford.eventmanager.presentation.common.view.activity.BaseActivity
import graeme.hosford.eventmanager.presentation.profile.create.CAMERA_REQUEST_CODE
import graeme.hosford.eventmanager.presentation.profile.create.CreateProfilePresenter
import graeme.hosford.eventmanager.presentation.profile.create.CreateProfileView
import javax.inject.Inject

class CreateProfileActivity : BaseActivity(), CreateProfileView {

    @Inject
    lateinit var presenter: CreateProfilePresenter

    private var binding: ActivityCreateProfileBinding? = null
    private val safeBinding get() = binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        EventManagerApplication.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityCreateProfileBinding.inflate(layoutInflater)
        setContentView(safeBinding.root)
        presenter.onViewCreated(this)

        safeBinding.createProfilePictureImageView.setOnClickListener {
            presenter.onProfileImageClick()
        }

        safeBinding.createProfileConfirmCreateButton.setOnClickListener {
            val firstName = safeBinding.createProfileEnterFirstNameEditText.text.toString()
            val lastName = safeBinding.createProfileEnterLastNameEditText.text.toString()
            val jobTitle = safeBinding.createProfileEnterJobTitleEditText.text.toString()
            val description = safeBinding.createProfileEnterDescriptionEditText.text.toString()

            presenter.onCreateProfileButtonClick(firstName, lastName, jobTitle, description)
        }
    }

    override fun startCamera() {
        startActivityForResult(CoreIntents.openCameraIntent(), CAMERA_REQUEST_CODE)
    }

    override fun setProfileImage(image: Bitmap) {
        safeBinding.createProfilePictureImageView.setImageBitmap(image)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        presenter.onActivityResult(requestCode, resultCode, data)
    }
}