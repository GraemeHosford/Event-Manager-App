package graeme.hosford.eventmanager.presentation.company.join

import graeme.hosford.eventmanager.presentation.common.presenter.Presenter

interface JoinCompanyPresenter : Presenter<JoinCompanyView> {

    fun onJoinCompanyClick(id: String)

}