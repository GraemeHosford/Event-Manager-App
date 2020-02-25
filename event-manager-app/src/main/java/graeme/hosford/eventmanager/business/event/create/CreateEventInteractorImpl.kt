package graeme.hosford.eventmanager.business.event.create

import graeme.hosford.eventmanager.business.common.BaseInteractor
import graeme.hosford.eventmanager.data.event.create.CreateEventFirebaseAccess
import graeme.hosford.eventmanager.entity.event.Event
import java.util.*
import javax.inject.Inject

class CreateEventInteractorImpl @Inject constructor(
    private val createEventFirebaseAccess: CreateEventFirebaseAccess
) : BaseInteractor<CreateEventInteractor.CreateEventCallback>(),
    CreateEventInteractor {

    override fun onCreate() {
        super.onCreate()
        createEventFirebaseAccess.setEventSaveCallback(EventSavedCallback())
    }

    override fun createEvent(
        name: String,
        description: String,
        location: String,
        attendees: ArrayList<String>,
        startDate: Long,
        endDate: Long
    ) {
        createEventFirebaseAccess.saveEvent(
            hashMapOf(
                Event.NAME_FIELD to name,
                Event.DESCRIPTION_FIELD to description,
                Event.LOCATION_FIELD to location,
                Event.INVITEES_LIST to attendees,
                Event.START_DATE to startDate,
                Event.END_DATE to endDate
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