package graeme.hosford.eventmanager.presentation.company.detail.presentation

import graeme.hosford.eventmanager.business.company.detail.CompanyDetailInteractor
import graeme.hosford.eventmanager.entity.company.Person
import graeme.hosford.eventmanager.presentation.common.model.UiModelListProcessor
import graeme.hosford.eventmanager.presentation.common.presenter.BasePresenter
import graeme.hosford.eventmanager.presentation.company.detail.CompanyDetailPresenter
import graeme.hosford.eventmanager.presentation.company.detail.CompanyDetailView
import graeme.hosford.eventmanager.presentation.company.detail.model.CompanyMemberComparator
import graeme.hosford.eventmanager.presentation.company.detail.model.CompanyMemberUiModel
import graeme.hosford.eventmanager.presentation.company.detail.model.CompanyMemberUiModelProcessor
import javax.inject.Inject

class CompanyDetailPresenterImpl @Inject constructor(
    private val interactor: CompanyDetailInteractor,
    private val processor: CompanyMemberUiModelProcessor
) : BasePresenter<CompanyDetailView, CompanyDetailInteractor>(interactor),
    CompanyDetailPresenter {

    override fun onViewCreated(view: CompanyDetailView) {
        super.onViewCreated(view)
        interactor.registerCallback(CompanyDetailInteractorCallback())
        processor.registerProcessingCallback(ProcessorCallback())

        processor.setListComparator(CompanyMemberComparator.memberNameComparator)
        getCompanyMembers()
    }

    override fun getCompanyMembers() {
        interactor.getCompanyMembers()
    }

    override fun onCompanyMemberClick(id: String) {

    }

    private inner class CompanyDetailInteractorCallback :
        CompanyDetailInteractor.CompanyDetailCallback {
        override fun onUserCompanyIdRetrieved(companyId: String) {

        }

        override fun onMembersRetrieved(people: List<Person>) {
            processor.process(people)
        }

        override fun onDataLoadFailure() {
            view?.showErrorScreen()
        }
    }

    private inner class ProcessorCallback :
        UiModelListProcessor.ProcessingCompleteCallback<CompanyMemberUiModel> {
        override fun onProcessingComplete(models: List<CompanyMemberUiModel>) {
            view?.showData(models)
        }

        override fun onProcessingFailure() {
            view?.showErrorScreen()
        }
    }
}