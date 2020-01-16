package graeme.hosford.eventmanager.presentation.company.join.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import butterknife.BindView
import butterknife.ButterKnife
import graeme.hosford.eventmanager.EventManagerApplication
import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.presentation.common.view.BaseActivity
import graeme.hosford.eventmanager.presentation.company.join.JoinCompanyView
import graeme.hosford.eventmanager.presentation.company.join.presentation.JoinCompanyPresenterImpl
import graeme.hosford.eventmanager.presentation.event.list.view.EventListActivity
import javax.inject.Inject

class JoinCompanyActivity : BaseActivity(), JoinCompanyView {

    @Inject
    lateinit var presenter: JoinCompanyPresenterImpl

    @BindView(R.id.enter_company_id_edit_text)
    lateinit var idEditText: EditText

    @BindView(R.id.join_company_button)
    lateinit var joinCompanyButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        EventManagerApplication.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_company)
        unbinder = ButterKnife.bind(this)

        presenter.onViewCreated(this)

        joinCompanyButton.setOnClickListener {
            presenter.onJoinCompanyClick(idEditText.text.toString())
        }
    }

    override fun showEventList() {
        startActivity(Intent(this, EventListActivity::class.java))
        finish()
    }
}