package graeme.hosford.eventmanager.data.event.list.converter

import com.google.firebase.firestore.DocumentSnapshot
import graeme.hosford.eventmanager.data.common.entity.EntityConverter
import graeme.hosford.eventmanager.entity.event.Event
import javax.inject.Inject

class EventEntityConverter @Inject constructor() : EntityConverter<Event> {

    override fun convert(document: DocumentSnapshot): Event {
        return Event(
            document.getString(Event.NAME_FIELD) ?: "Test Name",
            document.getString(Event.DESCRIPTION_FIELD) ?: "Test Description",
            document.getString(Event.LOCATION_FIELD) ?: "Test Location",
            document.get(Event.ATTENDEES_LIST, ArrayList::class.java) as ArrayList<String>?
                ?: arrayListOf()
        )
    }
}