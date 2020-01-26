package graeme.hosford.eventmanager.presentation.company.join.presentation

import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.business.company.join.JoinCompanyInteractor
import graeme.hosford.eventmanager.business.user.CurrentUserInteractor
import graeme.hosford.eventmanager.presentation.common.presenter.BasePresenter
import graeme.hosford.eventmanager.presentation.company.join.JoinCompanyPresenter
import graeme.hosford.eventmanager.presentation.company.join.JoinCompanyView
import javax.inject.Inject

class JoinCompanyPresenterImpl @Inject constructor(
    private val interactor: JoinCompanyInteractor,
    private val currentUserInteractor: CurrentUserInteractor
) : BasePresenter<JoinCompanyView, JoinCompanyInteractor>(interactor),
    JoinCompanyPresenter {

    override fun onViewCreated(view: JoinCompanyView) {
        super.onViewCreated(view)
        interactor.registerCallback(JoinCompanyListener())
        currentUserInteractor.registerCallback(AddUserListener())
    }

    override fun onJoinCompanyClick(id: String) {
        interactor.addCurrentUserToCompany(id)
    }

    private inner class JoinCompanyListener : JoinCompanyInteractor.JoinCompanyListener {
        override fun onJoinCompanySuccess(companyId: String) {
            currentUserInteractor.setUserCompany(companyId)
        }

        override fun onJoinCompanyFailure() {
            view?.showLongToast(R.string.company_join_failure_message)
        }
    }

    private inner class AddUserListener : CurrentUserInteractor.AddUserCompanyListener {
        override fun onAddUserCompanySuccess() {
            view?.showMainActivity()
        }

        override fun onAddUserCompanyFailure() {
            view?.showLongToast(R.string.company_join_failure_message)
        }
    }

}