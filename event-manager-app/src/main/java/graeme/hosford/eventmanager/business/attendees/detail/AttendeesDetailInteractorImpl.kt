package graeme.hosford.eventmanager.business.attendees.detail

import graeme.hosford.eventmanager.business.common.BaseInteractor
import graeme.hosford.eventmanager.data.attendees.AttendeesFirebaseAccess
import graeme.hosford.eventmanager.entity.company.Person
import javax.inject.Inject

class AttendeesDetailInteractorImpl @Inject constructor(
    private val attendeesFirebaseAccess: AttendeesFirebaseAccess
) : BaseInteractor<AttendeesDetailInteractor.AttendeesDetailCallback>(), AttendeesDetailInteractor {

    override fun onCreate() {
        super.onCreate()
        attendeesFirebaseAccess.setCallback(AttendeesDetailCallback())
    }

    override fun getEventAttendees(emails: List<String>) {
        attendeesFirebaseAccess.getEventAttendees(emails)
    }

    private inner class AttendeesDetailCallback : AttendeesFirebaseAccess.AttendeesDetailCallback {
        override fun onAttendeesRetrieved(attendees: List<Person>) {
            callback?.onAttendeesRetrieved(attendees)
        }

        override fun onAttendeeRetrievedFail() {
            callback?.onAttendeeRetrievalFailed()
        }
    }
}