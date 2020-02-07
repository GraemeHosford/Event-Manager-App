package graeme.hosford.eventmanager.data.attendees

interface AttendeesFirebaseAccess {

    fun getEventAttendees(companyId: String, eventId: String)

}