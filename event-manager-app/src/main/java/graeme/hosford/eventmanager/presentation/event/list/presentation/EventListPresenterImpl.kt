package graeme.hosford.eventmanager.presentation.event.list.presentation

import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.business.event.list.EventListInteractor
import graeme.hosford.eventmanager.presentation.common.model.UiModelListProcessor
import graeme.hosford.eventmanager.presentation.common.presenter.BasePresenter
import graeme.hosford.eventmanager.presentation.event.list.EventListPresenter
import graeme.hosford.eventmanager.presentation.event.list.EventListView
import graeme.hosford.eventmanager.presentation.event.list.model.EventListItemUiModel
import graeme.hosford.eventmanager.presentation.event.list.model.EventListItemUiModelProcessor
import javax.inject.Inject

class EventListPresenterImpl @Inject constructor(
    private val processor: EventListItemUiModelProcessor,
    private val interactor: EventListInteractor
) : BasePresenter<EventListView, EventListInteractor>(interactor),
    EventListPresenter {

    override fun onViewCreated(view: EventListView) {
        super.onViewCreated(view)
        processor.registerProcessingCallback(EventListProcessorCallback())
    }

    override fun onEventItemClick() {
        view?.showEventDetail()
    }

    private inner class EventListProcessorCallback :
        UiModelListProcessor.ProcessingCompleteCallback<EventListItemUiModel> {
        override fun onProcessingComplete(models: List<EventListItemUiModel>) {
            view?.showData(models)
        }

        override fun onProcessingFailure() {
            view?.showLongToast(R.string.generic_error_loading_data)
        }
    }
}