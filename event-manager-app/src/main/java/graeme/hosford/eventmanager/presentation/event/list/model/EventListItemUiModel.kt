package graeme.hosford.eventmanager.presentation.event.list.model

import java.util.*

class EventListItemUiModel(
    val id: String,
    val eventName: String,
    val eventDesc: String,
    val startDate: Calendar,
    val endDate: Calendar,
    val eventLocation: String,
    val attendees: ArrayList<String>
)