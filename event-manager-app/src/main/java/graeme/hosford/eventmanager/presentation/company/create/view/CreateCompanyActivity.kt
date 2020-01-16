package graeme.hosford.eventmanager.presentation.company.create.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import butterknife.BindView
import butterknife.ButterKnife
import graeme.hosford.eventmanager.EventManagerApplication
import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.presentation.common.view.BaseActivity
import graeme.hosford.eventmanager.presentation.company.create.CreateCompanyView
import graeme.hosford.eventmanager.presentation.company.create.presentation.CreateCompanyPresenterImpl
import graeme.hosford.eventmanager.presentation.event.list.view.EventListActivity
import javax.inject.Inject

class CreateCompanyActivity : BaseActivity(), CreateCompanyView {

    @Inject
    lateinit var presenter: CreateCompanyPresenterImpl

    @BindView(R.id.enter_company_name_edit_text)
    lateinit var companyNameEditText: EditText

    @BindView(R.id.confirm_create_company_button)
    lateinit var confirmCreateCompanyButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        EventManagerApplication.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_company)
        unbinder = ButterKnife.bind(this)

        presenter.onViewCreated(this)

        confirmCreateCompanyButton.setOnClickListener {
            presenter.onCreateCompanyButtonClick(companyNameEditText.text.toString())
        }
    }

    override fun showEventList() {
        startActivity(Intent(this, EventListActivity::class.java))
        finish()
    }
}