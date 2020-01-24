package graeme.hosford.eventmanager.presentation.company.create.presentation

import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.business.company.create.CreateCompanyInteractor
import graeme.hosford.eventmanager.presentation.common.presenter.BasePresenter
import graeme.hosford.eventmanager.presentation.company.create.CreateCompanyPresenter
import graeme.hosford.eventmanager.presentation.company.create.CreateCompanyView
import javax.inject.Inject

class CreateCompanyPresenterImpl @Inject constructor(
    private val interactor: CreateCompanyInteractor
) : BasePresenter<CreateCompanyView, CreateCompanyInteractor>(interactor),
    CreateCompanyPresenter {

    override fun onViewCreated(view: CreateCompanyView) {
        super.onViewCreated(view)
        interactor.registerCallback(CompanyListener())
    }

    override fun onCreateCompanyButtonClick(name: String) {
        interactor.getCompanyId(name)
    }

    private inner class CompanyListener : CreateCompanyInteractor.CreateCompanyListener {
        override fun onGetCompanyIdSuccess(id: Int, name: String) {
            interactor.saveCompany(id, name)
        }

        override fun onGetCompanyIdFailure() {
            view?.showLongToast(R.string.error_creating_company)
        }

        override fun onSaveCompanySuccess() {
            view?.showMainActivity()
        }

        override fun onSaveCompanyFailure() {
            view?.showLongToast(R.string.error_creating_company)
        }
    }
}