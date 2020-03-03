package graeme.hosford.eventmanager.presentation.profile.create.view

import android.os.Bundle
import graeme.hosford.eventmanager.EventManagerApplication
import graeme.hosford.eventmanager.databinding.ActivityCreateProfileBinding
import graeme.hosford.eventmanager.presentation.common.view.activity.BaseActivity
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
            presenter.onCreateProfileButtonClick()
        }
    }
}