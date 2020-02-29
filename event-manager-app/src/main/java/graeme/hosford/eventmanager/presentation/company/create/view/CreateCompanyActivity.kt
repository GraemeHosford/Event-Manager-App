package graeme.hosford.eventmanager.presentation.company.create.view

import android.content.Intent
import android.os.Bundle
import butterknife.ButterKnife
import graeme.hosford.eventmanager.EventManagerApplication
import graeme.hosford.eventmanager.databinding.ActivityCreateCompanyBinding
import graeme.hosford.eventmanager.presentation.common.view.activity.BaseActivity
import graeme.hosford.eventmanager.presentation.company.create.CreateCompanyPresenter
import graeme.hosford.eventmanager.presentation.company.create.CreateCompanyView
import graeme.hosford.eventmanager.presentation.main.view.MainActivity
import javax.inject.Inject

class CreateCompanyActivity : BaseActivity(), CreateCompanyView {

    @Inject
    lateinit var presenter: CreateCompanyPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        EventManagerApplication.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        val binding = ActivityCreateCompanyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        unbinder = ButterKnife.bind(this)

        presenter.onViewCreated(this)

        binding.confirmCreateCompanyButton.setOnClickListener {
            presenter.onCreateCompanyButtonClick(binding.enterCompanyNameEditText.text.toString())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }

    override fun showMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}