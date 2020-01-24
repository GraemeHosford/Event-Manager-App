package graeme.hosford.eventmanager.data.event.list

import com.google.firebase.firestore.FirebaseFirestore
import graeme.hosford.eventmanager.data.company.CompanyFirebaseAccess
import graeme.hosford.eventmanager.data.event.list.converter.EventEntityConverter
import graeme.hosford.eventmanager.entity.event.Event
import javax.inject.Inject

class EventListFirebaseAccessImpl @Inject constructor(
    private val eventConverter: EventEntityConverter
) : EventListFirebaseAccess {

    private var listener: EventListFirebaseAccess.EventDataListener? = null

    override fun setEventListener(listener: EventListFirebaseAccess.EventDataListener) {
        this.listener = listener
    }

    override fun getAllEvents(companyId: String) {
        FirebaseFirestore.getInstance()
            .collection(CompanyFirebaseAccess.COMPANIES_COLLECTION)
            .document(companyId)
            .collection(EventListFirebaseAccess.EVENTS_SUBCOLLECTION)
            .get()
            .addOnSuccessListener { d ->
                val entities = ArrayList<Event>()
                d.documents.forEach {
                    entities.add(eventConverter.convert(it))
                }
                listener?.onEventRetrieveSuccess(entities)
            }.addOnFailureListener {
                listener?.onEventRetrieveFailure()
            }
    }
}