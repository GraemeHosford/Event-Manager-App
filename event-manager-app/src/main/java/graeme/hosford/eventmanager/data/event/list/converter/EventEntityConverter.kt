package graeme.hosford.eventmanager.data.event.list.converter

import com.google.firebase.firestore.DocumentSnapshot
import graeme.hosford.eventmanager.data.common.entity.EntityConverter
import graeme.hosford.eventmanager.entity.event.Event
import javax.inject.Inject

class EventEntityConverter @Inject constructor() : EntityConverter<Event> {

    override fun convert(document: DocumentSnapshot): Event {
        with(document.data) {
            return Event(
                document.id,
                getOrDefault(this, Event.NAME_FIELD, "") as String,
                getOrDefault(this, Event.DESCRIPTION_FIELD, "") as String,
                getOrDefault(this, Event.LOCATION_FIELD, "") as String,
                getOrDefault(this, Event.ATTENDEES_LIST, arrayListOf<String>()) as ArrayList<String>
            )
        }
    }

    private fun getOrDefault(data: MutableMap<String, Any>?, key: String, defaultValue: Any): Any =
        data?.get(key) ?: defaultValue

}