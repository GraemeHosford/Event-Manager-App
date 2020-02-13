package graeme.hosford.eventmanager.business.event.detail

import graeme.hosford.eventmanager.business.common.BaseInteractor
import javax.inject.Inject

class EventDetailInteractorImpl @Inject constructor() :
    BaseInteractor<EventDetailInteractor.EventDetailCallback>()