package graeme.hosford.eventmanager.data.event.list.converter

import com.google.firebase.firestore.DocumentSnapshot
import graeme.hosford.eventmanager.data.common.entity.EntityConverter
import graeme.hosford.eventmanager.entity.event.Event
import java.util.*
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class EventEntityConverter @Inject constructor() : EntityConverter<Event> {

    override fun convert(document: DocumentSnapshot): Event {
        with(document.data) {
            return Event(
                document.id,
                getOrDefault(this, Event.OWNER, ""),
                getOrDefault(this, Event.NAME_FIELD, ""),
                getOrDefault(this, Event.DESCRIPTION_FIELD, ""),
                getCalendarFromLong(document.getLong(Event.START_DATE) ?: 0L),
                getCalendarFromLong(document.getLong(Event.END_DATE) ?: 0L),
                getOrDefault(this, Event.LOCATION_NAME_FIELD, ""),
                getOrDefault(this, Event.LOCATION_LAT_FIELD, 0.0),
                getOrDefault(this, Event.LOCATION_LONG_FIELD, 0.0),
                getOrDefault(this, Event.INVITEES_LIST, arrayListOf()),
                getOrDefault(this, Event.ATTENDEES_LIST, arrayListOf())
            )
        }
    }

    private fun <T> getOrDefault(data: MutableMap<String, Any>?, key: String, defaultValue: T): T =
        data?.get(key) as T ?: defaultValue

    private fun getCalendarFromLong(millis: Long): Calendar {
        val cal = Calendar.getInstance()
        cal.timeInMillis = millis
        return cal
    }

}