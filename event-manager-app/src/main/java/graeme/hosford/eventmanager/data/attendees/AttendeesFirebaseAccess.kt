package graeme.hosford.eventmanager.data.attendees

import graeme.hosford.eventmanager.entity.company.Person

interface AttendeesFirebaseAccess {

    interface AttendeesDetailCallback {
        fun onAttendeesRetrieved(attendees: List<Person>)

        fun onAttendeeRetrievedFail()
    }

    fun setCallback(callback: AttendeesFirebaseAccess.AttendeesDetailCallback)

    fun getEventAttendees(emails: List<String>)

}