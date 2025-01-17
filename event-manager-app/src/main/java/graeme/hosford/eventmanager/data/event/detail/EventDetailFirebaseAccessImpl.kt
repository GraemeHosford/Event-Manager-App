package graeme.hosford.eventmanager.data.event.detail

import com.google.firebase.firestore.FirebaseFirestore
import graeme.hosford.eventmanager.data.company.CompanyFirebaseAccess.Companion.COMPANIES_COLLECTION
import graeme.hosford.eventmanager.data.event.list.EventListFirebaseAccess.Companion.EVENTS_SUBCOLLECTION
import graeme.hosford.eventmanager.data.event.list.converter.EventEntityConverter
import graeme.hosford.eventmanager.data.login.USERS_COLLECTION
import javax.inject.Inject

class EventDetailFirebaseAccessImpl @Inject constructor(
    private val converter: EventEntityConverter
): EventDetailFirebaseAccess {

    private lateinit var callback: EventDetailFirebaseAccess.EventDetailCallback

    override fun registerCallback(callback: EventDetailFirebaseAccess.EventDetailCallback) {
        this.callback = callback
    }

    override fun getEventDetails(companyId: String, eventId: String) {
        FirebaseFirestore.getInstance()
            .collection(COMPANIES_COLLECTION)
            .document(companyId)
            .collection(EVENTS_SUBCOLLECTION)
            .document(eventId)
            .get()
            .addOnSuccessListener {
                callback.onEventRetrieved(converter.convert(it))
            }.addOnFailureListener {
                callback.onEventRetrievalFailed()
            }
    }

    override fun getUserEventDetails(userId: String, eventId: String) {
        FirebaseFirestore.getInstance()
            .collection(USERS_COLLECTION)
            .document(userId)
            .get()
            .addOnSuccessListener { doc ->
                FirebaseFirestore.getInstance()
                    .collection(COMPANIES_COLLECTION)
                    .document(doc.getString("companyId")!!)
                    .collection(EVENTS_SUBCOLLECTION)
                    .document(eventId)
                    .get()
                    .addOnSuccessListener {
                        callback.onEventRetrieved(converter.convert(it))
                    }.addOnFailureListener {
                        callback.onEventRetrievalFailed()
                    }
            }.addOnFailureListener {
                callback.onEventRetrievalFailed()
            }
    }
}