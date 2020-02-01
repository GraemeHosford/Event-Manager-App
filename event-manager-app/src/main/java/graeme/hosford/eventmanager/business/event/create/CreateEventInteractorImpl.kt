package graeme.hosford.eventmanager.business.event.create

import graeme.hosford.eventmanager.business.common.BaseInteractor
import graeme.hosford.eventmanager.data.event.create.CreateEventFirebaseAccess
import graeme.hosford.eventmanager.entity.event.Event
import javax.inject.Inject

class CreateEventInteractorImpl @Inject constructor(
    private val createEventFirebaseAccess: CreateEventFirebaseAccess
) : BaseInteractor<CreateEventInteractor.CreateEventCallback>(),
    CreateEventInteractor {

    override fun onCreate() {
        super.onCreate()
        createEventFirebaseAccess.setEventSaveCallback(EventSavedCallback())
    }

    override fun createEvent(name: String, description: String, location: String) {
        createEventFirebaseAccess.saveEvent(
            hashMapOf(
                Event.NAME_FIELD to name,
                Event.DESCRIPTION_FIELD to description,
                Event.LOCATION_FIELD to location
            )
        )
    }

    private inner class EventSavedCallback : CreateEventFirebaseAccess.EventCreatedCallback {
        override fun onEventSavedSuccessfully() {
            callback?.onEventSaved()
        }

        override fun onEventSaveFailure() {
            callback?.onEventSaveFailed()
        }
    }
}