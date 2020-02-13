package graeme.hosford.eventmanager.entity.event

data class Event(
    /* Default params used so Firebase can deserialize objects */
    val id: String,
    val name: String = "",
    val description: String = "",
    val location: String = "",
    val attendees: ArrayList<String> = arrayListOf()
) {
    companion object {
        const val NAME_FIELD = "name"
        const val DESCRIPTION_FIELD = "description"
        const val LOCATION_FIELD = "location"
        const val ATTENDEES_LIST = "attendees"
        const val START_DATE = "StartDate"
        const val END_DATE = "EndDate"
    }
}