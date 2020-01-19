package graeme.hosford.eventmanager.presentation.company.join.presentation

import graeme.hosford.eventmanager.business.company.join.JoinCompanyInteractor
import graeme.hosford.eventmanager.presentation.common.presenter.BasePresenter
import graeme.hosford.eventmanager.presentation.company.join.JoinCompanyPresenter
import graeme.hosford.eventmanager.presentation.company.join.JoinCompanyView
import javax.inject.Inject

class JoinCompanyPresenterImpl @Inject constructor(
    private val interactor: JoinCompanyInteractor
) : BasePresenter<JoinCompanyView, JoinCompanyInteractor>(interactor),
    JoinCompanyPresenter {

    override fun onJoinCompanyClick(id: String) {
        /* Interactor stuff here */
        view.showEventList()
    }
}