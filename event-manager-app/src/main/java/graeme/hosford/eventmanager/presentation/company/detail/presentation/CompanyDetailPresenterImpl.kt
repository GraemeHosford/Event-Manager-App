package graeme.hosford.eventmanager.presentation.company.detail.presentation

import graeme.hosford.eventmanager.business.company.detail.CompanyDetailInteractor
import graeme.hosford.eventmanager.presentation.common.presenter.BasePresenter
import graeme.hosford.eventmanager.presentation.company.detail.CompanyDetailPresenter
import graeme.hosford.eventmanager.presentation.company.detail.CompanyDetailView
import javax.inject.Inject

class CompanyDetailPresenterImpl @Inject constructor(
    private val interactor: CompanyDetailInteractor
) : BasePresenter<CompanyDetailView, CompanyDetailInteractor>(interactor),
    CompanyDetailPresenter