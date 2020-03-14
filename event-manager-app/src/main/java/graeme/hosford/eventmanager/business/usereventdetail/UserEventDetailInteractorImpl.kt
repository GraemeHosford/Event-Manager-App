package graeme.hosford.eventmanager.business.usereventdetail

import graeme.hosford.eventmanager.business.common.BaseInteractor
import javax.inject.Inject

class UserEventDetailInteractorImpl @Inject constructor() :
    BaseInteractor<UserEventDetailInteractor.UserEventDetailInteractorCallback>(),
    UserEventDetailInteractor