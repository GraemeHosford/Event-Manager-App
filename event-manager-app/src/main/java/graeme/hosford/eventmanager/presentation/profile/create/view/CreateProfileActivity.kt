package graeme.hosford.eventmanager.presentation.profile.create.view

import android.os.Bundle
import graeme.hosford.eventmanager.databinding.ActivityCreateProfileBinding
import graeme.hosford.eventmanager.presentation.common.view.activity.BaseActivity

class CreateProfileActivity : BaseActivity() {

    private var binding: ActivityCreateProfileBinding? = null
    private val safeBinding get() = binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateProfileBinding.inflate(layoutInflater)
        setContentView(safeBinding.root)
    }
}