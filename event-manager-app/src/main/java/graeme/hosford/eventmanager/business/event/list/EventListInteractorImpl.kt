package graeme.hosford.eventmanager.business.event.list

import graeme.hosford.eventmanager.business.common.BaseInteractor
import graeme.hosford.eventmanager.data.event.list.EventListFirebaseAccess
import javax.inject.Inject

class EventListInteractorImpl @Inject constructor(
    eventListFirebaseAccess: EventListFirebaseAccess
) : BaseInteractor<EventListInteractor.EventListCallback>(), EventListInteractor {
}