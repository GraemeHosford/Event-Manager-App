package graeme.hosford.eventmanager.business.event.create

import graeme.hosford.eventmanager.business.common.Interactor
import java.util.*

interface CreateEventInteractor : Interactor<CreateEventInteractor.CreateEventCallback> {

    interface CreateEventCallback {
        fun onEventSaved()

        fun onEventSaveFailed()
    }

    fun createEvent(
        name: String,
        description: String,
        location: String,
        attendees: ArrayList<String>,
        startDate: Long,
        endDate: Long
    )

}