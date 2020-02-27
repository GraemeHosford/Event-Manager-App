package graeme.hosford.eventmanager.presentation.event.create.presentation

import android.app.Activity
import android.content.Intent
import android.content.res.Resources
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
                view?.updateAttendeesText(attendees)
            }
        }
    }

    override fun getAttendeesDescriptionText(
        resources: Resources,
        attendees: ArrayList<String>?
    ): String {
        return if (attendees.isNullOrEmpty()) {
            resources.getString(R.string.create_event_no_attendees_chosen)
        } else {
            resources.getQuantityString(R.plurals.num_attendees, attendees.size)
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

        view?.updateStartDateText()
    }

    override fun endDateChosen(year: Int, month: Int, dayOfMonth: Int) {
        endYear = year
        endMonth = month
        endDayOfMonth = dayOfMonth

        view?.updateEndDateText()
    }

    override fun startTimeChosen(hourofDay: Int, minute: Int) {
        startHour = hourofDay
        startMinute = minute

        view?.updateStartTimeText()
    }

    override fun endTimeChosen(hourofDay: Int, minute: Int) {
        endHour = hourofDay
        endMinute = minute

        view?.updateEndTimeText()
    }

    override fun getStartDateDescriptionText(): String {
        return "${startDayOfMonth}/${startMonth}/${startYear}"
    }

    override fun getEndDateDescriptionText(): String {
        return "${endDayOfMonth}/${endMonth}/${endYear}"
    }

    override fun getStartTimeDescriptionText(): String {
        return "${startHour}:${startMinute}"
    }

    override fun getEndTimeDescriptionText(): String {
        return "${endHour}:${endMinute}"
    }

    /* Add list of accomodation */

    override fun getDefaultStartHour(): Int {
        return if (startHour == 0) {
            val cal = Calendar.getInstance()
            cal.get(Calendar.HOUR_OF_DAY)
        } else {
            startHour
        }
    }

    override fun getDefaultStartMinute(): Int {
        return if (startMinute == 0) {
            val cal = Calendar.getInstance()
            cal.get(Calendar.MINUTE)
        } else {
            startMinute
        }
    }

    override fun getDefaultStartDayOfMonth(): Int {
        return if (startDayOfMonth == 0) {
            val cal = Calendar.getInstance()
            cal.get(Calendar.DAY_OF_MONTH)
        } else {
            startDayOfMonth
        }
    }

    override fun getDefaultStartMonth(): Int {
        return if (startMonth == 0) {
            val cal = Calendar.getInstance()
            cal.get(Calendar.MONTH)
        } else {
            startMonth
        }
    }

    override fun getDefaultStartYear(): Int {
        return if (startYear == 0) {
            val cal = Calendar.getInstance()
            cal.get(Calendar.YEAR)
        } else {
            startYear
        }
    }

    override fun getDefaultEndHour(): Int {
        return if (endHour == 0) {
            val cal = Calendar.getInstance()
            cal.get(Calendar.HOUR_OF_DAY)
        } else {
            endHour
        }
    }

    override fun getDefaultEndMinute(): Int {
        return if (endMinute == 0) {
            val cal = Calendar.getInstance()
            cal.get(Calendar.MINUTE)
        } else {
            endMinute
        }
    }

    override fun getDefaultEndDayOfMonth(): Int {
        return if (endDayOfMonth == 0) {
            val cal = Calendar.getInstance()
            cal.get(Calendar.DAY_OF_MONTH)
        } else {
            endDayOfMonth
        }
    }

    override fun getDefaultEndMonth(): Int {
        return if (endMonth == 0) {
            val cal = Calendar.getInstance()
            cal.get(Calendar.MONTH)
        } else {
            endMonth
        }
    }

    override fun getDefaultEndYear(): Int {
        return if (endYear == 0) {
            val cal = Calendar.getInstance()
            cal.get(Calendar.YEAR)
        } else {
            endYear
        }
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

            interactor.createEvent(
                name,
                description,
                location,
                attendees,
                startCal.timeInMillis,
                endCal.timeInMillis
            )
        }
    }

    override fun getInvitedAttendees(): ArrayList<String> = attendees!!

    private inner class InteractorCallback : CreateEventInteractor.CreateEventCallback {
        override fun onEventSaved() {
            view?.showLongToast(R.string.create_event_success)
            view?.closeCreateEventScreen()
        }

        override fun onEventSaveFailed() {
            view?.showLongToast(R.string.create_event_error)
        }
    }
}