package graeme.hosford.eventmanager.presentation.event.create.view

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.fragment.findNavController
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import graeme.hosford.eventmanager.EventManagerApplication
import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.databinding.FragmentCreateEventBinding
import graeme.hosford.eventmanager.presentation.CoreIntents
import graeme.hosford.eventmanager.presentation.attendees.choose.view.AttendeesActivity
import graeme.hosford.eventmanager.presentation.common.view.fragment.BaseFragment
import graeme.hosford.eventmanager.presentation.event.create.CreateEventPresenter
import graeme.hosford.eventmanager.presentation.event.create.CreateEventView
import graeme.hosford.eventmanager.presentation.notification.service.EventManagerNotificationService
import graeme.hosford.eventmanager.presentation.notification.service.EventResponseService
import java.util.*
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

    override fun showNotification(eventId: String) {
        fakeNotificationOutput(
            eventId,
            safeBinding.enterEventNameEditText.text.toString(),
            presenter.getInvitedAttendees()
        )
    }

    /*
    * Notifications were working correctly without this faked data but API keys for Firebase were compromised
    * and Google shut down account. Despite gaining access to the account once again the cloud functions stuff
    * which made the notifications work treats the API key as still being deactivated despite
    * creating a new one and updating all references to it in cloud functions code.
    * At this point in the semester (April 16th) and with plenty of other assignments still to do it
    * is just too late to spend hours playing around with cloud functions in an attempt to get them working again.
    *
    * Good thing about this faked data though is that it will still function exactly the same as the
    * cloud function did only with the possibility that now if an event is not created successfully
    * the notification will still show.
    *
    * The notification will also only show on the device of the event creator and not on the device od
    * any invited person
    * */
    private fun fakeNotificationOutput(
        eventId: String,
        eventTitle: String,
        attendees: List<String>
    ) {
        if (attendees.isEmpty()) {
            return
        }

        val notificationHandler = Handler()

        notificationHandler.postDelayed(
            {
                val notificationManager =
                    requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

                val pendingIntent =
                    PendingIntent.getActivity(
                        requireContext(),
                        0,
                        CoreIntents.startAppIntent(requireContext()),
                        PendingIntent.FLAG_UPDATE_CURRENT
                    )

                val notBuilder = NotificationCompat.Builder(
                    requireContext(),
                    EventManagerNotificationService.CHANNEL_ID
                ).setContentTitle(eventTitle)
                    .setContentText("Tap here to view your events!")
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.drawable.ic_calendar)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)

                notBuilder.addAction(
                    R.drawable.ic_calendar, "Accept", getActionPendingIntent(
                        eventId,
                        "Accept",
                        0
                    )
                )

                notBuilder.addAction(
                    R.drawable.ic_calendar, "Decline", getActionPendingIntent(
                        eventId,
                        "Decline",
                        1
                    )
                )

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    val channel = NotificationChannel(
                        EventManagerNotificationService.CHANNEL_ID,
                        "Event Manager",
                        NotificationManager.IMPORTANCE_DEFAULT
                    )

                    notificationManager.createNotificationChannel(channel)
                }

                with(NotificationManagerCompat.from(requireContext())) {
                    notify(1, notBuilder.build())
                }
            },
            delay()
        )
    }

    private fun delay(): Long {
        var rand = Random().nextInt(10)
        rand += 5
        rand *= 1000
        return rand.toLong()
    }

    private fun getActionPendingIntent(
        eventId: String,
        actionValue: String,
        requestCode: Int
    ): PendingIntent {
        val intent = Intent(context, EventResponseService::class.java)
        intent.putExtra("Action", actionValue)
        intent.putExtra("EventID", eventId)

        return PendingIntent.getService(
            context,
            requestCode,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
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