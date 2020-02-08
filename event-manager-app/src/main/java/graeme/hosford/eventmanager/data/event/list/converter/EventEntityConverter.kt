package graeme.hosford.eventmanager.data.event.list.converter

import com.google.firebase.firestore.DocumentSnapshot
import graeme.hosford.eventmanager.data.common.entity.EntityConverter
import graeme.hosford.eventmanager.entity.event.Event
import javax.inject.Inject

class EventEntityConverter @Inject constructor() : EntityConverter<Event> {

    override fun convert(document: DocumentSnapshot): Event {
        return document.toObject(Event::class.java)!!
    }
}