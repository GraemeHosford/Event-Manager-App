package graeme.hosford.eventmanager.business.event.create

import com.google.android.libraries.places.api.model.Place
import graeme.hosford.eventmanager.business.common.BaseInteractor
import graeme.hosford.eventmanager.data.event.create.CreateEventFirebaseAccess
import graeme.hosford.eventmanager.data.login.CurrentUserNetworkAccess
import graeme.hosford.eventmanager.entity.event.Event
import java.util.*
import javax.inject.Inject

class CreateEventInteractorImpl @Inject constructor(
    private val createEventFirebaseAccess: CreateEventFirebaseAccess,
    private val currentUserNetworkAccess: CurrentUserNetworkAccess
) : BaseInteractor<CreateEventInteractor.CreateEventCallback>(),
    CreateEventInteractor {

    override fun onCreate() {
        super.onCreate()
        createEventFirebaseAccess.setEventSaveCallback(EventSavedCallback())
    }

    override fun createEvent(
        name: String,
        description: String,
        location: Place,
        attendees: ArrayList<String>,
        startDate: Long,
        endDate: Long
    ) {
        createEventFirebaseAccess.saveEvent(
            hashMapOf(
                Event.NAME_FIELD to name,
                Event.DESCRIPTION_FIELD to description,
                Event.LOCATION_NAME_FIELD to location.name!!,
                Event.LOCATION_LAT_FIELD to location.latLng!!.latitude,
                Event.LOCATION_LONG_FIELD to location.latLng!!.longitude,
                Event.INVITEES_LIST to attendees,
                Event.ATTENDEES_LIST to emptyList<String>(),
                Event.START_DATE to startDate,
                Event.END_DATE to endDate,
                Event.OWNER to currentUserNetworkAccess.getCurrentUser()!!.email!!
            )
        )
    }

    private inner class EventSavedCallback : CreateEventFirebaseAccess.EventCreatedCallback {
        override fun onEventSavedSuccessfully(eventId: String) {
            callback?.onEventSaved(eventId)
        }

        override fun onEventSaveFailure() {
            callback?.onEventSaveFailed()
        }
    }
}