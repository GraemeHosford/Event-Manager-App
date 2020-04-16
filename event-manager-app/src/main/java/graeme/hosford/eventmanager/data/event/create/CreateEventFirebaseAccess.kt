package graeme.hosford.eventmanager.data.event.create

interface CreateEventFirebaseAccess {

    interface EventCreatedCallback {
        fun onEventSavedSuccessfully(eventId: String)

        fun onEventSaveFailure()
    }

    fun setEventSaveCallback(callback: EventCreatedCallback)

    fun saveEvent(eventDetails: HashMap<String, Any>)

}