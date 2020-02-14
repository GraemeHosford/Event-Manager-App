package graeme.hosford.eventmanager.data.event.list.converter

import com.google.firebase.firestore.DocumentSnapshot
import graeme.hosford.eventmanager.data.common.entity.EntityConverter
import graeme.hosford.eventmanager.entity.event.Event
import java.util.*
import javax.inject.Inject

class EventEntityConverter @Inject constructor() : EntityConverter<Event> {

    override fun convert(document: DocumentSnapshot): Event {
        with(document.data) {
            return Event(
                document.id,
                getOrDefault(this, Event.NAME_FIELD, "") as String,
                getOrDefault(this, Event.DESCRIPTION_FIELD, "") as String,
                getCalendarFromLong(getOrDefault(this, Event.START_DATE, 0) as Long),
                getCalendarFromLong(getOrDefault(this, Event.END_DATE, 0) as Long),
                getOrDefault(this, Event.LOCATION_FIELD, "") as String,
                getOrDefault(this, Event.ATTENDEES_LIST, arrayListOf<String>()) as ArrayList<String>
            )
        }
    }

    private fun getOrDefault(data: MutableMap<String, Any>?, key: String, defaultValue: Any): Any =
        data?.get(key) ?: defaultValue

    private fun getCalendarFromLong(millis: Long): Calendar {
        val cal = Calendar.getInstance()
        cal.timeInMillis = millis
        return cal
    }

}