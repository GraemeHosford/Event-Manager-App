package graeme.hosford.eventmanager.presentation.company.create

import graeme.hosford.eventmanager.presentation.common.presenter.Presenter

interface CreateCompanyPresenter : Presenter<CreateCompanyView> {

    fun onCreateCompanyButtonClick(name: String)

}