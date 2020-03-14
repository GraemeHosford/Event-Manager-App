package graeme.hosford.eventmanager.data.usereventdetail

const val EVENT_DETAILS_COLLECTION = "EventDetails"

const val SUBJECT_KEY = "Subject"
const val DETAILS_KEY = "Details"

interface UserEventDetailFirebaseAccess {

    var callback: UserEventDetailsFirebaseAccessCallback?

    interface UserEventDetailsFirebaseAccessCallback {
        fun onDetailsSaved()

        fun onDetailsSaveFailed()
    }

    fun saveEventDetails(userId: String, eventId: String, subject: String, details: String)

}