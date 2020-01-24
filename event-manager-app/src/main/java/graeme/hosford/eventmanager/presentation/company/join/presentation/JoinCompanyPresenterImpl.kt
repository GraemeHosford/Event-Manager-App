package graeme.hosford.eventmanager.presentation.company.join.presentation

import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.business.company.join.JoinCompanyInteractor
import graeme.hosford.eventmanager.presentation.common.presenter.BasePresenter
import graeme.hosford.eventmanager.presentation.company.join.JoinCompanyPresenter
import graeme.hosford.eventmanager.presentation.company.join.JoinCompanyView
import javax.inject.Inject

class JoinCompanyPresenterImpl @Inject constructor(
    private val interactor: JoinCompanyInteractor
) : BasePresenter<JoinCompanyView, JoinCompanyInteractor>(interactor),
    JoinCompanyPresenter {

    override fun onViewCreated(view: JoinCompanyView) {
        super.onViewCreated(view)
        interactor.registerCallback(JoinCompanyListener())
    }

    override fun onJoinCompanyClick(id: String) {
        interactor.addCurrentUserToCompany(id.toInt())
    }

    private inner class JoinCompanyListener : JoinCompanyInteractor.JoinCompanyListener {
        override fun onJoinCompanySuccess() {
            view?.showLongToast(R.string.company_join_success_message)
            view?.showMainActivity()
        }

        override fun onJoinCompanyFailure() {
            view?.showLongToast(R.string.company_join_failure_message)
        }
    }

}