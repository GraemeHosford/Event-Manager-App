package graeme.hosford.eventmanager.entity.event

data class Event(
    val name: String,
    val description: String,
    val location: String,
    val attendees: ArrayList<String>
) {
    companion object {
        const val NAME_FIELD = "name"
        const val DESCRIPTION_FIELD = "Description"
        const val LOCATION_FIELD = "Location"
        const val ATTENDEES_LIST = "Attendees"
        const val START_DATE = "StartDate"
        const val END_DATE = "EndDate"
    }
}