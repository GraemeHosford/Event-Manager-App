package graeme.hosford.eventmanager.business.attendees.detail

import graeme.hosford.eventmanager.business.common.Interactor

interface AttendeesDetailInteractor :
    Interactor<AttendeesDetailInteractor.AttendeesDetailCallback> {

    interface AttendeesDetailCallback

    fun getEventAttendees(eventId: String)

}