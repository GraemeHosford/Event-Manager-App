package graeme.hosford.eventmanager.presentation.attendees.detail.presentation

import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.business.attendees.detail.AttendeesDetailInteractor
import graeme.hosford.eventmanager.entity.company.Person
import graeme.hosford.eventmanager.presentation.attendees.choose.model.AttendeesUiModel
import graeme.hosford.eventmanager.presentation.attendees.choose.model.AttendeesUiModelProcessor
import graeme.hosford.eventmanager.presentation.attendees.detail.AttendeesDetailPresenter
import graeme.hosford.eventmanager.presentation.attendees.detail.AttendeesDetailView
import graeme.hosford.eventmanager.presentation.common.model.UiModelListProcessor
import graeme.hosford.eventmanager.presentation.common.presenter.BasePresenter
import javax.inject.Inject

class AttendeesDetailPresenterImpl @Inject constructor(
    private val interactor: AttendeesDetailInteractor,
    private val processor: AttendeesUiModelProcessor
) : BasePresenter<AttendeesDetailView, AttendeesDetailInteractor>(interactor),
    AttendeesDetailPresenter {

    override fun onViewCreated(view: AttendeesDetailView) {
        super.onViewCreated(view)
        interactor.registerCallback(InteractorCallback())
        processor.registerProcessingCallback(ProcessingCallback())
    }

    override fun showAttendees(attendees: ArrayList<String>) {
        interactor.getEventAttendees(attendees)
    }

    override fun onAttendeeClick(id: String) {
        view?.showAttendeeOptions(id)
    }

    private inner class InteractorCallback : AttendeesDetailInteractor.AttendeesDetailCallback {
        override fun onAttendeesRetrieved(attendees: List<Person>) {
            processor.process(attendees)
        }

        override fun onAttendeeRetrievalFailed() {
            view?.showLongToast(R.string.generic_error_loading_data)
        }
    }

    private inner class ProcessingCallback :
        UiModelListProcessor.ProcessingCompleteCallback<AttendeesUiModel> {
        override fun onProcessingComplete(models: List<AttendeesUiModel>) {
            view?.showData(models)
        }

        override fun onProcessingFailure() {
            view?.showLongToast(R.string.generic_error_loading_data)
        }
    }
}