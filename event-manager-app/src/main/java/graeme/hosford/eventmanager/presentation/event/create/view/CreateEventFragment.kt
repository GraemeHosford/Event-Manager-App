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
import graeme.hosford.eventmanager.presentation.common.view.custom.SummaryTextView
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

    @BindView(R.id.create_event_choose_start_date_button)
    lateinit var chooseStartDate: SummaryTextView

    @BindView(R.id.create_event_choose_end_date_button)
    lateinit var chooseEndDate: SummaryTextView

    @BindView(R.id.create_event_choose_start_time_button)
    lateinit var chooseStartTime: SummaryTextView

    @BindView(R.id.create_event_choose_end_time_button)
    lateinit var chooseEndTime: SummaryTextView

    @BindView(R.id.choose_attendees_button)
    lateinit var chooseAttendees: SummaryTextView

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

    override fun updateStartDateText() {
        chooseStartDate.setDescriptionText(presenter.getStartDateDescriptionText())
    }

    override fun updateEndDateText() {
        chooseEndDate.setDescriptionText(presenter.getEndDateDescriptionText())
    }

    override fun updateStartTimeText() {
        chooseStartTime.setDescriptionText(presenter.getStartTimeDescriptionText())
    }

    override fun updateEndTimeText() {
        chooseEndTime.setDescriptionText(presenter.getEndTimeDescriptionText())
    }

    override fun updateAttendeesText(attendees: ArrayList<String>?) {
        chooseAttendees.setDescriptionText(
            presenter.getAttendeesDescriptionText(
                resources,
                attendees
            )
        )
    }

    override fun showStartDatePicker() {
        DatePickerDialog(
            requireContext(),
            DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                presenter.startDateChosen(year, month, dayOfMonth)
            },
            presenter.getDefaultStartYear(),
            presenter.getDefaultStartMonth(),
            presenter.getDefaultStartDayOfMonth()
        ).show()
    }

    override fun showEndDatePicker() {
        DatePickerDialog(
            requireContext(),
            DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                presenter.endDateChosen(year, month, dayOfMonth)
            },
            presenter.getDefaultEndYear(),
            presenter.getDefaultEndMonth(),
            presenter.getDefaultEndDayOfMonth()
        ).show()
    }

    override fun showStartTimePicker() {
        TimePickerDialog(
            requireContext(),
            TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                presenter.startTimeChosen(
                    hourOfDay,
                    minute
                )
            },
            presenter.getDefaultStartHour(),
            presenter.getDefaultStartMinute(),
            true
        ).show()
    }

    override fun showEndTimePicker() {
        TimePickerDialog(
            requireContext(),
            TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                presenter.endTimeChosen(
                    hourOfDay,
                    minute
                )
            },
            presenter.getDefaultEndHour(),
            presenter.getDefaultEndMinute(),
            true
        ).show()
    }
}