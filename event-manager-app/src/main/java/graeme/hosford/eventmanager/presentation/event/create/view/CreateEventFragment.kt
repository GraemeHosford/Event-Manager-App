package graeme.hosford.eventmanager.presentation.event.create.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import butterknife.BindView
import butterknife.ButterKnife
import graeme.hosford.eventmanager.EventManagerApplication
import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.presentation.attendees.view.AttendeesActivity
import graeme.hosford.eventmanager.presentation.common.view.fragment.BaseFragment
import graeme.hosford.eventmanager.presentation.event.create.CreateEventPresenter
import graeme.hosford.eventmanager.presentation.event.create.CreateEventView
import javax.inject.Inject

class CreateEventFragment : BaseFragment(), CreateEventView {

    @Inject
    lateinit var presenter: CreateEventPresenter

    @BindView(R.id.enter_event_name_edit_text)
    lateinit var eventName: EditText

    @BindView(R.id.enter_event_description_edit_text)
    lateinit var eventDescription: EditText

    @BindView(R.id.enter_event_location_edit_text)
    lateinit var eventLocation: EditText

    @BindView(R.id.choose_attendees_button)
    lateinit var chooseAttendees: Button

    @BindView(R.id.create_event_button)
    lateinit var createEvent: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        EventManagerApplication.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        presenter.onViewCreated(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_create_event, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        chooseAttendees.setOnClickListener {
            presenter.onChooseAttendeesButtonClick()
        }

        createEvent.setOnClickListener {
            presenter.onCreateEventButtonClick(
                eventName.text.toString(),
                eventDescription.text.toString(),
                eventLocation.text.toString(),
                presenter.getInvitedAttendees()
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        presenter.onActivityResult(requestCode, resultCode, data)
    }

    override fun showChooseAttendeesFragment() {
        /* Need to use this to get choice of attendees back. Navigation component currently
        does not support a startForResult operation. This isn't ideal but only option for now */
        startActivityForResult(
            Intent(context, AttendeesActivity::class.java),
            CreateEventView.CHOOSE_ATTENDEES_REQUEST_CODE
        )
    }
}