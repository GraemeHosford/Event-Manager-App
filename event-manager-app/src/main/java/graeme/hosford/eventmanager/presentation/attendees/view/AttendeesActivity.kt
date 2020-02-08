package graeme.hosford.eventmanager.presentation.attendees.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import butterknife.BindView
import butterknife.ButterKnife
import graeme.hosford.eventmanager.EventManagerApplication
import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.presentation.attendees.AttendeesPresenter
import graeme.hosford.eventmanager.presentation.attendees.AttendeesView
import graeme.hosford.eventmanager.presentation.attendees.model.AttendeesUiModel
import graeme.hosford.eventmanager.presentation.attendees.view.adapter.AttendeeClickListener
import graeme.hosford.eventmanager.presentation.attendees.view.adapter.AttendeesAdapter
import graeme.hosford.eventmanager.presentation.attendees.view.adapter.AttendeesViewHolder
import graeme.hosford.eventmanager.presentation.common.view.activity.BaseRecyclerViewActivity
import javax.inject.Inject

class AttendeesActivity :
    BaseRecyclerViewActivity<AttendeesUiModel, AttendeesViewHolder, AttendeesAdapter>(),
    AttendeesView {

    @Inject
    lateinit var presenter: AttendeesPresenter

    @BindView(R.id.confirm_attendees_button)
    lateinit var confirmAttendeesButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        EventManagerApplication.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        ButterKnife.bind(this)
        presenter.onViewCreated(this)

        confirmAttendeesButton.setOnClickListener {
            presenter.onConfirmAttendeesButtonClick()
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun returnAttendeeEmails() {
        val intent = Intent()

        intent.putStringArrayListExtra(
            AttendeesView.ATTENDEE_EMAILS_KEY,
            presenter.getAttendees()
        )

        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    override fun getRecyclerViewLayout(): Int = R.layout.choose_attendees_layout

    override fun recyclerViewAdapter(): AttendeesAdapter {
        return AttendeesAdapter(
            object : AttendeeClickListener {
                override fun onAttendeeClick(email: String) {
                    presenter.onAttendeeClick(email)
                }
            }
        )
    }
}