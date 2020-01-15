package graeme.hosford.eventmanager.presentation.company.join.view

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import graeme.hosford.eventmanager.EventManagerApplication
import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.presentation.common.view.BaseActivity
import graeme.hosford.eventmanager.presentation.company.join.JoinCompanyView
import graeme.hosford.eventmanager.presentation.company.join.presentation.JoinCompanyPresenterImpl
import javax.inject.Inject

class JoinCompanyActivity : BaseActivity(), JoinCompanyView {

    @Inject
    lateinit var presenter: JoinCompanyPresenterImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        EventManagerApplication.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_company)

        val idEditText = findViewById<EditText>(R.id.enter_company_id_edit_text)

        findViewById<Button>(R.id.join_company_button).setOnClickListener {
            presenter.onJoinCompanyClick(idEditText.text.toString())
        }

    }
}