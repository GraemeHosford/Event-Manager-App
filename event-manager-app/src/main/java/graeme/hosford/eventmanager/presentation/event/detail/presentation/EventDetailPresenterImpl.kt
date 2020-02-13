package graeme.hosford.eventmanager.presentation.event.detail.presentation

import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.business.event.detail.EventDetailInteractor
import graeme.hosford.eventmanager.entity.event.Event
import graeme.hosford.eventmanager.presentation.common.model.UiModelSingleProcessor
import graeme.hosford.eventmanager.presentation.common.presenter.BasePresenter
import graeme.hosford.eventmanager.presentation.event.detail.EventDetailPresenter
import graeme.hosford.eventmanager.presentation.event.detail.EventDetailView
import graeme.hosford.eventmanager.presentation.event.detail.model.EventDetailUiModel
import graeme.hosford.eventmanager.presentation.event.detail.model.EventDetailUiModelProcessor
import javax.inject.Inject

class EventDetailPresenterImpl @Inject constructor(
    private val interactor: EventDetailInteractor,
    private val processor: EventDetailUiModelProcessor
) : BasePresenter<EventDetailView, EventDetailInteractor>(interactor), EventDetailPresenter {

    override fun onViewCreated(view: EventDetailView) {
        super.onViewCreated(view)
        interactor.registerCallback(EventDetailInteractorCallback())
        processor.registerProcessingCallback(ProcessorCallback())
    }

    override fun loadEventDetail(eventId: String) {
        if (eventId.isNotEmpty()) {
            interactor.retrieveEvent(eventId)
        } else {
            view?.showLongToast(R.string.generic_error_loading_data)
        }
    }

    private inner class EventDetailInteractorCallback : EventDetailInteractor.EventDetailCallback {
        override fun onEventLoaded(event: Event) {
            processor.process(event)
        }

        override fun onLoadFailure() {
            view?.showLongToast(R.string.generic_error_loading_data)
        }
    }

    private inner class ProcessorCallback: UiModelSingleProcessor.ProcessingCompleteCallback<EventDetailUiModel> {
        override fun onProcessingComplete(model: EventDetailUiModel) {

        }

        override fun onProcessingFailed() {
            view?.showLongToast(R.string.generic_error_loading_data)
        }
    }
}