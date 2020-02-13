package graeme.hosford.eventmanager.presentation.event.list.model

class EventListItemUiModel(
    val id: String,
    val eventName: String,
    val eventDesc: String,
    val eventLocation: String,
    val attendees: ArrayList<String>
)