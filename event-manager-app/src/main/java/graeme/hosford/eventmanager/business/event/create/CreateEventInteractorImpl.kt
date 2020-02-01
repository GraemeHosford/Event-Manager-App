package graeme.hosford.eventmanager.business.event.create

import graeme.hosford.eventmanager.business.common.BaseInteractor
import javax.inject.Inject

class CreateEventInteractorImpl @Inject constructor() :
    BaseInteractor<CreateEventInteractor.CreateEventCallback>(),
    CreateEventInteractor