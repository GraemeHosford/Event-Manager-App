package graeme.hosford.eventmanager.presentation.company.join.view

import android.content.Intent
import android.os.Bundle
import graeme.hosford.eventmanager.EventManagerApplication
import graeme.hosford.eventmanager.databinding.ActivityJoinCompanyBinding
import graeme.hosford.eventmanager.presentation.common.view.activity.BaseActivity
import graeme.hosford.eventmanager.presentation.company.join.JoinCompanyPresenter
import graeme.hosford.eventmanager.presentation.company.join.JoinCompanyView
import graeme.hosford.eventmanager.presentation.main.view.MainActivity
import javax.inject.Inject

class JoinCompanyActivity : BaseActivity(), JoinCompanyView {

    @Inject
    lateinit var presenter: JoinCompanyPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        EventManagerApplication.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        val binding = ActivityJoinCompanyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter.onViewCreated(this)

        binding.joinCompanyButton.setOnClickListener {
            presenter.onJoinCompanyClick(binding.enterCompanyIdEditText.text.toString())
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