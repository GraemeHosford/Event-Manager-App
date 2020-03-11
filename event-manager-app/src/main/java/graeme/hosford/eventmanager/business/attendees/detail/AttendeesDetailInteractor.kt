package graeme.hosford.eventmanager.business.attendees.detail

import graeme.hosford.eventmanager.business.common.Interactor
import graeme.hosford.eventmanager.entity.company.Person

interface AttendeesDetailInteractor :
    Interactor<AttendeesDetailInteractor.AttendeesDetailCallback> {

    interface AttendeesDetailCallback {
        fun onAttendeesRetrieved(attendees: List<Person>)

        fun onAttendeeRetrievalFailed()
    }

    fun getEventAttendees(emails: List<String>)

}