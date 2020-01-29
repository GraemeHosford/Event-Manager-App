package graeme.hosford.eventmanager.presentation.company.create_join_screen.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import butterknife.BindView
import butterknife.ButterKnife
import graeme.hosford.eventmanager.EventManagerApplication
import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.presentation.common.view.activity.BaseActivity
import graeme.hosford.eventmanager.presentation.company.create.view.CreateCompanyActivity
import graeme.hosford.eventmanager.presentation.company.create_join_screen.CreateJoinCompanyView
import graeme.hosford.eventmanager.presentation.company.join.view.JoinCompanyActivity

class CreateJoinCompanyActivity : BaseActivity(), CreateJoinCompanyView {

    @BindView(R.id.create_company_button)
    lateinit var createCompanyButton: Button

    @BindView(R.id.join_company_button)
    lateinit var joinCompanyButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        EventManagerApplication.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_join_company)
        unbinder = ButterKnife.bind(this)

        createCompanyButton.setOnClickListener {
            startCreateCompanyFlow()
        }

        joinCompanyButton.setOnClickListener {
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