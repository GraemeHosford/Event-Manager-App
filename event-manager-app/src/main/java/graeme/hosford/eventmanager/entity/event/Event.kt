package graeme.hosford.eventmanager.entity.event

data class Event(val name: String, val description: String, val location: String) {

    companion object {
        const val NAME_FIELD = "name"
        const val DESCRIPTION_FIELD = "Description"
        const val LOCATION_FIELD = "Location"
    }

}