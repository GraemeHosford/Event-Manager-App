package graeme.hosford.eventmanager.data.attendees

import com.google.firebase.firestore.FirebaseFirestore
import graeme.hosford.eventmanager.data.login.USERS_COLLECTION
import graeme.hosford.eventmanager.data.login.converter.PersonEntityConverter
import graeme.hosford.eventmanager.entity.company.Person
import javax.inject.Inject

class AttendeesFirebaseAccessImpl @Inject constructor(
    private val entityConverter: PersonEntityConverter
) : AttendeesFirebaseAccess {

    private lateinit var callback: AttendeesFirebaseAccess.AttendeesDetailCallback

    override fun setCallback(callback: AttendeesFirebaseAccess.AttendeesDetailCallback) {
        this.callback = callback
    }

    override fun getEventAttendees(emails: List<String>) {
        FirebaseFirestore.getInstance()
            .collection(USERS_COLLECTION)
            .whereIn(Person.ID, emails)
            .get()
            .addOnSuccessListener {
                val entities = arrayListOf<Person>()
                it.documents.forEach {
                    entities.add(entityConverter.convert(it))
                }
                callback.onAttendeesRetrieved(entities)
            }.addOnFailureListener {
                callback.onAttendeeRetrievedFail()
            }
    }
}