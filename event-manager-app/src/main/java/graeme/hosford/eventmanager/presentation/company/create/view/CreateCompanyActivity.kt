package graeme.hosford.eventmanager.presentation.company.create.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import butterknife.BindView
import butterknife.ButterKnife
import graeme.hosford.eventmanager.EventManagerApplication
import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.presentation.common.view.activity.BaseActivity
import graeme.hosford.eventmanager.presentation.company.create.CreateCompanyPresenter
import graeme.hosford.eventmanager.presentation.company.create.CreateCompanyView
import graeme.hosford.eventmanager.presentation.main.view.MainActivity
import javax.inject.Inject

class CreateCompanyActivity : BaseActivity(), CreateCompanyView {

    @Inject
    lateinit var presenter: CreateCompanyPresenter

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

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }

    override fun showMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}