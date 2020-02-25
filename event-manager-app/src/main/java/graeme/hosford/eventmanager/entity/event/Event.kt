package graeme.hosford.eventmanager.entity.event

import java.util.*

data class Event(
    /* Default params used so Firebase can deserialize objects */
    val id: String,
    val name: String = "",
    val description: String = "",
    val startDate: Calendar,
    val endDate: Calendar,
    val location: String = "",
    val invitees: ArrayList<String> = arrayListOf(),
    val attendees: ArrayList<String> = arrayListOf()
) {
    companion object {
        const val NAME_FIELD = "name"
        const val DESCRIPTION_FIELD = "description"
        const val LOCATION_FIELD = "location"
        const val INVITEES_LIST = "invitees"
        const val ATTENDEES_LIST = "attendees"
        const val NOT_ATTENDING_LIST = "not_attending"
        const val START_DATE = "StartDate"
        const val END_DATE = "EndDate"
        const val OWNER = "Owner"
    }
}