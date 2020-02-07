package graeme.hosford.eventmanager.business.attendees

import graeme.hosford.eventmanager.business.common.BaseInteractor
import javax.inject.Inject

class AttendeesInteractorImpl @Inject constructor() :
    BaseInteractor<AttendeesInteractor.AttendeesCallback>(),
    AttendeesInteractor