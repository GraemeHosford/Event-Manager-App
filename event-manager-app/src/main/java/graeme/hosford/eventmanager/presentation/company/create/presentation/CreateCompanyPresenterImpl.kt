package graeme.hosford.eventmanager.presentation.company.create.presentation

import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.business.company.create.CreateCompanyInteractor
import graeme.hosford.eventmanager.business.user.CurrentUserInteractor
import graeme.hosford.eventmanager.presentation.common.presenter.BasePresenter
import graeme.hosford.eventmanager.presentation.company.create.CreateCompanyPresenter
import graeme.hosford.eventmanager.presentation.company.create.CreateCompanyView
import javax.inject.Inject

class CreateCompanyPresenterImpl @Inject constructor(
    private val interactor: CreateCompanyInteractor,
    private val currentUserInteractor: CurrentUserInteractor
) : BasePresenter<CreateCompanyView, CreateCompanyInteractor>(interactor),
    CreateCompanyPresenter {

    override fun onViewCreated(view: CreateCompanyView) {
        super.onViewCreated(view)
        currentUserInteractor.onCreate()
        interactor.registerCallback(CompanyListener())
        currentUserInteractor.registerCallback(UserInteractorCallback())
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

        override fun onSaveCompanySuccess(companyId: String) {
            currentUserInteractor.setUserCompany(companyId)
        }

        override fun onSaveCompanyFailure() {
            view?.showLongToast(R.string.error_creating_company)
        }
    }

    private inner class UserInteractorCallback : CurrentUserInteractor.UserCompanyListener {
        override fun onAddUserCompanySuccess() {
            view?.showMainActivity()
        }

        override fun onAddUserCompanyFailure() {
            view?.showLongToast(R.string.error_creating_company)
        }

        override fun onUserInfoRetrieved(info: Any?) {
        }

        override fun onUserInfoRetrievalFailed() {
        }
    }
}