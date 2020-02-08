package graeme.hosford.eventmanager.presentation.attendees.presentation

import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.business.attendees.AttendeesInteractor
import graeme.hosford.eventmanager.entity.company.Member
import graeme.hosford.eventmanager.presentation.attendees.AttendeesPresenter
import graeme.hosford.eventmanager.presentation.attendees.AttendeesView
import graeme.hosford.eventmanager.presentation.attendees.model.AttendeesUiModel
import graeme.hosford.eventmanager.presentation.attendees.model.AttendeesUiModelProcessor
import graeme.hosford.eventmanager.presentation.common.model.UiModelListProcessor
import graeme.hosford.eventmanager.presentation.common.presenter.BasePresenter
import javax.inject.Inject

class AttendeesPresenterImpl @Inject constructor(
    private val interactor: AttendeesInteractor,
    private val processor: AttendeesUiModelProcessor
) : BasePresenter<AttendeesView, AttendeesInteractor>(interactor),
    AttendeesPresenter {

    private val attendees: ArrayList<String> = ArrayList()

    override fun onViewCreated(view: AttendeesView) {
        super.onViewCreated(view)
        interactor.registerCallback(AttendeesCallback())
        processor.registerProcessingCallback(ProcessorCallback())
    }

    override fun onResume() {
        super.onResume()
        interactor.getCompanyMembers()
    }

    override fun onAttendeeClick(email: String) {
        if (attendees.contains(email)) {
            attendees.remove(email)
        } else {
            attendees.add(email)
        }
    }

    override fun getAttendees(): ArrayList<String> = attendees

    private inner class AttendeesCallback : AttendeesInteractor.AttendeesCallback {
        override fun onCompanyMembersRetrieved(members: List<Member>) {
            processor.process(members)
        }

        override fun onMemberRetrievalFailed() {
            view?.showLongToast(R.string.generic_error_loading_data)
        }
    }

    private inner class ProcessorCallback :
        UiModelListProcessor.ProcessingCompleteCallback<AttendeesUiModel> {
        override fun onProcessingComplete(models: List<AttendeesUiModel>) {
            view?.showData(models)
        }

        override fun onProcessingFailure() {
            view?.showErrorScreen()
        }
    }
}