package graeme.hosford.eventmanager.business.profile.create

import graeme.hosford.eventmanager.business.common.BaseInteractor
import javax.inject.Inject

class CreateProfileInteractorImpl @Inject constructor() :
    BaseInteractor<CreateProfileInteractor.CreateProfileCallback>(),
    CreateProfileInteractor