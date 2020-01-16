package graeme.hosford.eventmanager.presentation.company.create.presentation

import graeme.hosford.eventmanager.presentation.common.presenter.BasePresenter
import graeme.hosford.eventmanager.presentation.company.create.CreateCompanyPresenter
import graeme.hosford.eventmanager.presentation.company.create.CreateCompanyView
import javax.inject.Inject

class CreateCompanyPresenterImpl @Inject constructor(

) : BasePresenter<CreateCompanyView>(), CreateCompanyPresenter {

    override fun onCreateCompanyButtonClick(name: String) {
        view.showEventList()
    }
}