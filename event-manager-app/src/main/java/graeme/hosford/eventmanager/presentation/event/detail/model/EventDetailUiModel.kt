package graeme.hosford.eventmanager.presentation.event.detail.model

import java.util.*

class EventDetailUiModel(
    val id: String,
    val name: String,
    val description: String,
    val startDate: Calendar,
    val endDate: Calendar,
    val location: String,
    val attendees: List<String>
)