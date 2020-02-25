package graeme.hosford.eventmanager.data.event.list

import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import graeme.hosford.eventmanager.data.company.CompanyFirebaseAccess
import graeme.hosford.eventmanager.data.event.list.converter.EventEntityConverter
import graeme.hosford.eventmanager.data.login.USERS_COLLECTION
import graeme.hosford.eventmanager.entity.event.Event
import javax.inject.Inject

class EventListFirebaseAccessImpl @Inject constructor(
    private val eventConverter: EventEntityConverter
) : EventListFirebaseAccess {

    private var listener: EventListFirebaseAccess.EventDataListener? = null

    override fun setEventListener(listener: EventListFirebaseAccess.EventDataListener) {
        this.listener = listener
    }

    override fun setAttendingStatus(eventId: String, userId: String, attending: Boolean) {
        /* For whoever is unfortunate enough to read this code I know it's terrible and
        I'm not happy about it either but we're now into the unfortunate phase of a college project
         where anything so long as it works is acceptable so ¯\_(ツ)_/¯ */
        FirebaseFirestore.getInstance()
            .collection(USERS_COLLECTION)
            .document(userId)
            .get()
            .addOnSuccessListener {
                val field = if (attending) {
                    Event.ATTENDEES_LIST
                } else {
                    Event.NOT_ATTENDING_LIST
                }

                FirebaseFirestore.getInstance()
                    .collection(CompanyFirebaseAccess.COMPANIES_COLLECTION)
                    .document(it.getString("companyId")!!)
                    .collection(EventListFirebaseAccess.EVENTS_SUBCOLLECTION)
                    .document(eventId)
                    .update(
                        field,
                        FieldValue.arrayUnion(userId)
                    ).addOnSuccessListener { _ ->
                        FirebaseFirestore.getInstance()
                            .collection(CompanyFirebaseAccess.COMPANIES_COLLECTION)
                            .document(it.getString("companyId")!!)
                            .collection(EventListFirebaseAccess.EVENTS_SUBCOLLECTION)
                            .document(eventId)
                            .update(
                                Event.INVITEES_LIST,
                                FieldValue.arrayRemove(userId)
                            )
                    }
            }
    }

    override fun getAllEvents(userEmail: String) {
        FirebaseFirestore.getInstance()
            .collection(USERS_COLLECTION)
            .document(userEmail)
            .get()
            .addOnSuccessListener {
                /* This nesting is not great but will work for now because of time constraints */
                FirebaseFirestore.getInstance()
                    .collection(CompanyFirebaseAccess.COMPANIES_COLLECTION)
                    .document(it.getString("companyId")!!)
                    .collection(EventListFirebaseAccess.EVENTS_SUBCOLLECTION)
                    .get()
                    .addOnSuccessListener { d ->
                        val entities = ArrayList<Event>()
                        d.documents.forEach { doc ->
                            entities.add(eventConverter.convert(doc))
                        }
                        listener?.onEventRetrieveSuccess(entities)
                    }.addOnFailureListener {
                        listener?.onEventRetrieveFailure()
                    }
            }.addOnFailureListener {
                listener?.onEventRetrieveFailure()
            }
    }
}