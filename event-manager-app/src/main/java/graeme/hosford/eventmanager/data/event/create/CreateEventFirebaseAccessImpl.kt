package graeme.hosford.eventmanager.data.event.create

import com.google.firebase.firestore.FirebaseFirestore
import graeme.hosford.eventmanager.data.company.CompanyFirebaseAccess
import graeme.hosford.eventmanager.data.event.list.EventListFirebaseAccess
import graeme.hosford.eventmanager.data.login.CurrentUserNetworkAccess
import graeme.hosford.eventmanager.data.login.USERS_COLLECTION
import javax.inject.Inject

class CreateEventFirebaseAccessImpl @Inject constructor(
    private val currentUserNetworkAccess: CurrentUserNetworkAccess
) : CreateEventFirebaseAccess {

    private lateinit var callback: CreateEventFirebaseAccess.EventCreatedCallback

    override fun setEventSaveCallback(callback: CreateEventFirebaseAccess.EventCreatedCallback) {
        this.callback = callback
    }

    override fun saveEvent(eventDetails: HashMap<String, Any>) {
        /* Yes this code is horrendous but things have become somewhat tangled up
        and it would take too long to fix them */
        FirebaseFirestore.getInstance()
            .collection(USERS_COLLECTION)
            .document(currentUserNetworkAccess.getCurrentUser()?.email!!)
            .get()
            .addOnSuccessListener {
                FirebaseFirestore.getInstance()
                    .collection(CompanyFirebaseAccess.COMPANIES_COLLECTION)
                    .document(it.getString("companyId")!!)
                    .collection(EventListFirebaseAccess.EVENTS_SUBCOLLECTION)
                    .add(
                        eventDetails
                    ).addOnSuccessListener { doc ->
                        callback.onEventSavedSuccessfully(doc.id)
                    }.addOnFailureListener {
                        callback.onEventSaveFailure()
                    }
            }.addOnFailureListener {
                callback.onEventSaveFailure()
            }
    }
}