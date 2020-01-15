package graeme.hosford.eventmanager.presentation.company.create_join_screen.presentation

import graeme.hosford.eventmanager.presentation.common.presenter.BasePresenter
import graeme.hosford.eventmanager.presentation.company.create_join_screen.CreateJoinCompanyPresenter
import graeme.hosford.eventmanager.presentation.company.create_join_screen.CreateJoinCompanyView
import javax.inject.Inject

class CreateJoinCompanyPresenterImpl @Inject constructor() : BasePresenter<CreateJoinCompanyView>(),
    CreateJoinCompanyPresenter {

    override fun onCreateCompanyClick() {
        view.startCreateCompanyFlow()
    }

    override fun onJoinCompanyClick() {
        view.startJoinCompanyFlow()
    }
}