package graeme.hosford.eventmanager.presentation.usereventdetail.presentation

import graeme.hosford.eventmanager.business.usereventdetail.UserEventDetailInteractor
import graeme.hosford.eventmanager.presentation.common.presenter.BasePresenter
import graeme.hosford.eventmanager.presentation.usereventdetail.AddUserEventDetailPresenter
import graeme.hosford.eventmanager.presentation.usereventdetail.AddUserEventDetailView
import javax.inject.Inject

class AddUserEventDetailPresenterImpl @Inject constructor(
    private val interactor: UserEventDetailInteractor
) : BasePresenter<AddUserEventDetailView, UserEventDetailInteractor>(interactor),
    AddUserEventDetailPresenter