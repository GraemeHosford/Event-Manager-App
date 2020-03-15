package graeme.hosford.eventmanager.data.usereventdetail

import graeme.hosford.eventmanager.entity.usereventdetail.UserEventDetail

const val EVENT_DETAILS_COLLECTION = "EventDetails"

const val SUBJECT_KEY = "Subject"
const val DETAILS_KEY = "Details"

interface UserEventDetailFirebaseAccess {

    var callback: UserEventDetailsFirebaseAccessCallback?

    var retrievalCallback: UserEventDetailsRetrievalCallback?

    interface UserEventDetailsFirebaseAccessCallback {
        fun onDetailsSaved()

        fun onDetailsSaveFailed()
    }

    interface UserEventDetailsRetrievalCallback {
        fun onDetailsRetrieved(entities: List<UserEventDetail>)

        fun onRetrievalFailed()
    }

    fun saveEventDetails(
        userId: String,
        eventId: String,
        eventName: String,
        subject: String,
        details: String
    )

    fun getUserEventDetails(userId: String)

}