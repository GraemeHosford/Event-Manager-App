package graeme.hosford.eventmanager.presentation.event.create.view

import android.app.DatePickerDialog
import android.app.TimePickerDialog
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
import java.util.*
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

    @BindView(R.id.create_event_choose_start_date_button)
    lateinit var chooseStartDate: Button

    @BindView(R.id.create_event_choose_end_date_button)
    lateinit var chooseEndDate: Button

    @BindView(R.id.create_event_choose_start_time_button)
    lateinit var chooseStartTime: Button

    @BindView(R.id.create_event_choose_end_time_button)
    lateinit var chooseEndTime: Button

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

        chooseStartDate.setOnClickListener {
            presenter.onChooseStartDateButtonClick()
        }

        chooseEndDate.setOnClickListener {
            presenter.onChooseEndDateButtonClick()
        }

        chooseStartTime.setOnClickListener {
            presenter.onChooseStartTimeButtonClick()
        }

        chooseEndTime.setOnClickListener {
            presenter.onChooseEndTimeButtonClick()
        }

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

    override fun showStartDatePicker() {
        val calendar = Calendar.getInstance()

        DatePickerDialog(
            requireContext(),
            DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                presenter.startDateChosen(year, month, dayOfMonth)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    override fun showEndDatePicker() {
        val calendar = Calendar.getInstance()

        DatePickerDialog(
            requireContext(),
            DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                presenter.endDateChosen(year, month, dayOfMonth)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    override fun showStartTimePicker() {
        val calendar = Calendar.getInstance()

        TimePickerDialog(
            requireContext(),
            TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                presenter.startTimeChosen(
                    hourOfDay,
                    minute
                )
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        ).show()
    }

    override fun showEndTimePicker() {
        val calendar = Calendar.getInstance()

        TimePickerDialog(
            requireContext(),
            TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                presenter.endTimeChosen(
                    hourOfDay,
                    minute
                )
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        ).show()
    }
}