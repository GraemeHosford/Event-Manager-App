package graeme.hosford.eventmanager.business.event.create

import com.google.android.libraries.places.api.model.Place
import graeme.hosford.eventmanager.business.common.Interactor
import java.util.*

interface CreateEventInteractor : Interactor<CreateEventInteractor.CreateEventCallback> {

    interface CreateEventCallback {
        fun onEventSaved(eventId: String)

        fun onEventSaveFailed()
    }

    fun createEvent(
        name: String,
        description: String,
        location: Place,
        attendees: ArrayList<String>,
        startDate: Long,
        endDate: Long
    )

}