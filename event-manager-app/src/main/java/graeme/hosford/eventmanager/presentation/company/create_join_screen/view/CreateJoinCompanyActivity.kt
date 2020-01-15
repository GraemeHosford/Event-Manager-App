package graeme.hosford.eventmanager.presentation.company.create_join_screen.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import graeme.hosford.eventmanager.EventManagerApplication
import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.presentation.common.view.BaseActivity
import graeme.hosford.eventmanager.presentation.company.create_join_screen.CreateJoinCompanyView
import graeme.hosford.eventmanager.presentation.company.create_join_screen.presentation.CreateJoinCompanyPresenterImpl
import graeme.hosford.eventmanager.presentation.company.join.view.JoinCompanyActivity
import javax.inject.Inject

class CreateJoinCompanyActivity : BaseActivity(), CreateJoinCompanyView {

    @Inject
    lateinit var presenter: CreateJoinCompanyPresenterImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        EventManagerApplication.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_join_company)

        presenter.onViewCreated(this)

        findViewById<Button>(R.id.create_company_button).setOnClickListener {
            presenter.onCreateCompanyClick()
        }

        findViewById<Button>(R.id.join_company_button).setOnClickListener {
            presenter.onJoinCompanyClick()
        }
    }

    override fun startCreateCompanyFlow() {
        showLongToast("Create test")
    }

    override fun startJoinCompanyFlow() {
        startActivity(Intent(this, JoinCompanyActivity::class.java))
    }
}