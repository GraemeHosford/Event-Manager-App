package graeme.hosford.eventmanager.business.company.detail

import graeme.hosford.eventmanager.business.common.BaseInteractor
import javax.inject.Inject

class CompanyDetailInteractorImpl @Inject constructor() :
    BaseInteractor<CompanyDetailInteractor.CompanyDetailCallback>(),
    CompanyDetailInteractor