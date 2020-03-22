package graeme.hosford.eventmanager.presentation.event.create.view

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import graeme.hosford.eventmanager.EventManagerApplication
import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.databinding.FragmentCreateEventBinding
import graeme.hosford.eventmanager.presentation.attendees.choose.view.AttendeesActivity
import graeme.hosford.eventmanager.presentation.common.view.fragment.BaseFragment
import graeme.hosford.eventmanager.presentation.event.create.CreateEventPresenter
import graeme.hosford.eventmanager.presentation.event.create.CreateEventView
import javax.inject.Inject

class CreateEventFragment : BaseFragment(), CreateEventView {

    @Inject
    lateinit var presenter: CreateEventPresenter

    private var binding: FragmentCreateEventBinding? = null
    private val safeBinding get() = binding!!

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
        binding = FragmentCreateEventBinding.inflate(inflater, container, false)
        return safeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val placeSelectionFragment =
            childFragmentManager.findFragmentById(R.id.place_selection_fragment) as AutocompleteSupportFragment

        placeSelectionFragment.setPlaceFields(
            listOf(
                Place.Field.ID,
                Place.Field.NAME,
                Place.Field.LAT_LNG
            )
        )

        placeSelectionFragment.setHint("Search Location")

        placeSelectionFragment.setOnPlaceSelectedListener(
            object : PlaceSelectionListener {
                override fun onPlaceSelected(place: Place) {
                    presenter.onPlaceSelected(place)
                }

                override fun onError(status: Status) {
                    showLongToast(R.string.generic_error_loading_data)
                }
            }
        )

        safeBinding.createEventChooseStartDateButton.setOnClickListener {
            presenter.onChooseStartDateButtonClick()
        }

        safeBinding.createEventChooseEndDateButton.setOnClickListener {
            presenter.onChooseEndDateButtonClick()
        }

        safeBinding.createEventChooseStartTimeButton.setOnClickListener {
            presenter.onChooseStartTimeButtonClick()
        }

        safeBinding.createEventChooseEndTimeButton.setOnClickListener {
            presenter.onChooseEndTimeButtonClick()
        }

        safeBinding.chooseAttendeesButton.setOnClickListener {
            presenter.onChooseAttendeesButtonClick()
        }

        safeBinding.createEventButton.setOnClickListener {
            presenter.onCreateEventButtonClick(
                safeBinding.enterEventNameEditText.text.toString(),
                safeBinding.enterEventDescriptionEditText.text.toString(),
                presenter.getInvitedAttendees()
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        presenter.onActivityResult(requestCode, resultCode, data)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun showChooseAttendeesFragment() {
        /* Need to use this to get choice of attendees back. Navigation component currently
        does not support a startForResult operation. This isn't ideal but only option for now */
        startActivityForResult(
            Intent(context, AttendeesActivity::class.java),
            CreateEventView.CHOOSE_ATTENDEES_REQUEST_CODE
        )
    }

    override fun closeCreateEventScreen() {
        findNavController().popBackStack()
    }

    override fun updateStartDateText() {
        safeBinding.createEventChooseStartDateButton.setDescriptionText(presenter.getStartDateDescriptionText())
    }

    override fun updateEndDateText() {
        safeBinding.createEventChooseEndDateButton.setDescriptionText(presenter.getEndDateDescriptionText())
    }

    override fun updateStartTimeText() {
        safeBinding.createEventChooseStartTimeButton.setDescriptionText(presenter.getStartTimeDescriptionText())
    }

    override fun updateEndTimeText() {
        safeBinding.createEventChooseEndTimeButton.setDescriptionText(presenter.getEndTimeDescriptionText())
    }

    override fun updateAttendeesText(attendees: ArrayList<String>?) {
        safeBinding.chooseAttendeesButton.setDescriptionText(
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