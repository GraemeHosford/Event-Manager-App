package graeme.hosford.eventmanager.presentation.event.create

import android.content.res.Resources
import com.google.android.libraries.places.api.model.Place
import graeme.hosford.eventmanager.presentation.common.presenter.Presenter

interface CreateEventPresenter : Presenter<CreateEventView> {

    fun onChooseAttendeesButtonClick()

    fun onCreateEventButtonClick(
        name: String,
        description: String,
        attendees: ArrayList<String>
    )

    fun onPlaceSelected(place: Place)

    fun getInvitedAttendees(): ArrayList<String>

    fun onChooseStartDateButtonClick()

    fun onChooseEndDateButtonClick()

    fun onChooseStartTimeButtonClick()

    fun onChooseEndTimeButtonClick()

    fun startDateChosen(year: Int, month: Int, dayOfMonth: Int)

    fun endDateChosen(year: Int, month: Int, dayOfMonth: Int)

    fun startTimeChosen(hourofDay: Int, minute: Int)

    fun endTimeChosen(hourofDay: Int, minute: Int)

    fun getStartDateDescriptionText(): String

    fun getEndDateDescriptionText(): String

    fun getStartTimeDescriptionText(): String

    fun getEndTimeDescriptionText(): String

    fun getAttendeesDescriptionText(resources: Resources, attendees: ArrayList<String>?): String

    fun getDefaultStartHour(): Int

    fun getDefaultStartMinute(): Int

    fun getDefaultStartDayOfMonth(): Int

    fun getDefaultStartMonth(): Int

    fun getDefaultStartYear(): Int

    fun getDefaultEndHour(): Int

    fun getDefaultEndMinute(): Int

    fun getDefaultEndDayOfMonth(): Int

    fun getDefaultEndMonth(): Int

    fun getDefaultEndYear(): Int
}