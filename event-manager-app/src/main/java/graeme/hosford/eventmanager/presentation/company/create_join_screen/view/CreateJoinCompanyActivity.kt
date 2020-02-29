package graeme.hosford.eventmanager.presentation.company.create_join_screen.view

import android.content.Intent
import android.os.Bundle
import butterknife.ButterKnife
import graeme.hosford.eventmanager.EventManagerApplication
import graeme.hosford.eventmanager.databinding.ActivityCreateJoinCompanyBinding
import graeme.hosford.eventmanager.presentation.common.view.activity.BaseActivity
import graeme.hosford.eventmanager.presentation.company.create.view.CreateCompanyActivity
import graeme.hosford.eventmanager.presentation.company.create_join_screen.CreateJoinCompanyView
import graeme.hosford.eventmanager.presentation.company.join.view.JoinCompanyActivity

class CreateJoinCompanyActivity : BaseActivity(), CreateJoinCompanyView {

    override fun onCreate(savedInstanceState: Bundle?) {
        EventManagerApplication.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        val binding = ActivityCreateJoinCompanyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        unbinder = ButterKnife.bind(this)

        binding.createCompanyButton.setOnClickListener {
            startCreateCompanyFlow()
        }

        binding.joinCompanyButton.setOnClickListener {
            startJoinCompanyFlow()
        }
    }

    override fun startCreateCompanyFlow() {
        startActivity(Intent(this, CreateCompanyActivity::class.java))
        finish()
    }

    override fun startJoinCompanyFlow() {
        startActivity(Intent(this, JoinCompanyActivity::class.java))
        finish()
    }
}