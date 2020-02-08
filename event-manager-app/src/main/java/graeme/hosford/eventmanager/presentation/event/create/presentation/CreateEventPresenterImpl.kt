package graeme.hosford.eventmanager.presentation.event.create.presentation

import android.app.Activity
import android.content.Intent
import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.business.event.create.CreateEventInteractor
import graeme.hosford.eventmanager.presentation.attendees.AttendeesView
import graeme.hosford.eventmanager.presentation.common.presenter.BasePresenter
import graeme.hosford.eventmanager.presentation.event.create.CreateEventPresenter
import graeme.hosford.eventmanager.presentation.event.create.CreateEventView
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class CreateEventPresenterImpl @Inject constructor(
    private val interactor: CreateEventInteractor
) : BasePresenter<CreateEventView, CreateEventInteractor>(interactor), CreateEventPresenter {

    private var attendees: ArrayList<String>? = ArrayList()

    private var startYear = 0
    private var startMonth = 0
    private var startDayOfMonth = 0

    private var endYear = 0
    private var endMonth = 0
    private var endDayOfMonth = 0

    private var startHour = 0
    private var startMinute = 0

    private var endHour = 0
    private var endMinute = 0

    override fun onViewCreated(view: CreateEventView) {
        super.onViewCreated(view)
        interactor.registerCallback(InteractorCallback())
    }

    override fun onChooseAttendeesButtonClick() {
        view?.showChooseAttendeesFragment()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CreateEventView.CHOOSE_ATTENDEES_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                attendees = data?.getStringArrayListExtra(AttendeesView.ATTENDEE_EMAILS_KEY)
            }
        }
    }

    override fun onChooseStartDateButtonClick() {
        view?.showStartDatePicker()
    }

    override fun onChooseEndDateButtonClick() {
        view?.showEndDatePicker()
    }

    override fun onChooseStartTimeButtonClick() {
        view?.showStartTimePicker()
    }

    override fun onChooseEndTimeButtonClick() {
        view?.showEndTimePicker()
    }

    override fun startDateChosen(year: Int, month: Int, dayOfMonth: Int) {
        startYear = year
        startMonth = month
        startDayOfMonth = dayOfMonth
    }

    override fun endDateChosen(year: Int, month: Int, dayOfMonth: Int) {
        endYear = year
        endMonth = month
        endDayOfMonth = dayOfMonth
    }

    override fun startTimeChosen(hourofDay: Int, minute: Int) {
        startHour = hourofDay
        startMinute = minute
    }

    override fun endTimeChosen(hourofDay: Int, minute: Int) {
        endHour = hourofDay
        endMinute = minute
    }

    override fun onCreateEventButtonClick(
        name: String,
        description: String,
        location: String,
        attendees: ArrayList<String>
    ) {
        if (name.isBlank() || description.isBlank() || location.isBlank()) {
            view?.showLongToast(R.string.create_event_error_empty_fields)
        } else {
            val startCal = Calendar.getInstance().apply {
                set(startYear, startMonth, startDayOfMonth, startHour, startMinute, 0)
            }

            val endCal = Calendar.getInstance().apply {
                set(endYear, endMonth, endDayOfMonth, endHour, endMinute, 0)
            }

            interactor.createEvent(name, description, location, attendees, startCal, endCal)
        }
    }

    override fun getInvitedAttendees(): ArrayList<String> = attendees!!

    private inner class InteractorCallback : CreateEventInteractor.CreateEventCallback {
        override fun onEventSaved() {
            view?.showLongToast(R.string.create_event_success)
        }

        override fun onEventSaveFailed() {
            view?.showLongToast(R.string.create_event_error)
        }
    }
}