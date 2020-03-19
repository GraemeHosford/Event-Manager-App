package graeme.hosford.eventmanager.presentation.event.detail.model

import com.google.android.gms.maps.model.LatLng
import java.util.*

class EventDetailUiModel(
    val id: String,
    val name: String,
    val description: String,
    val startDate: Calendar,
    val endDate: Calendar,
    val locationName: String,
    val locationLatLng: LatLng,
    val invitees: List<String>,
    val attendees: List<String>
)