package graeme.hosford.eventmanager.presentation.event.list.common.model

import java.util.*

class EventListItemUiModel(
    val id: String,
    val eventOwner: String,
    val eventName: String,
    val eventDesc: String,
    val startDate: Calendar,
    val endDate: Calendar,
    val eventLocation: String,
    val attendees: ArrayList<String>
) {
    fun shouldShowResponseOptions(currentUserId: String): Boolean {
        return currentUserId != eventOwner && !attendees.contains(currentUserId)
    }
}