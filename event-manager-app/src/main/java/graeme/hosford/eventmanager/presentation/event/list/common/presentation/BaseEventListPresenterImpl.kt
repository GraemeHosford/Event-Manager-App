package graeme.hosford.eventmanager.presentation.event.list.common.presentation

import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.business.event.list.EventListInteractor
import graeme.hosford.eventmanager.entity.event.Event
import graeme.hosford.eventmanager.presentation.common.model.UiModelListProcessor
import graeme.hosford.eventmanager.presentation.common.presenter.BasePresenter
import graeme.hosford.eventmanager.presentation.event.list.common.BaseEventListPresenter
import graeme.hosford.eventmanager.presentation.event.list.common.EventListView
import graeme.hosford.eventmanager.presentation.event.list.common.model.EventListItemUiModel
import graeme.hosford.eventmanager.presentation.event.list.common.model.EventListItemUiModelComparator
import graeme.hosford.eventmanager.presentation.event.list.common.model.EventListItemUiModelProcessor

abstract class BaseEventListPresenterImpl constructor(
    private val processor: EventListItemUiModelProcessor,
    private val interactor: EventListInteractor
) : BasePresenter<EventListView, EventListInteractor>(interactor),
    BaseEventListPresenter {

    override fun onViewCreated(view: EventListView) {
        super.onViewCreated(view)
        processor.registerProcessingCallback(EventListProcessorCallback())
        interactor.registerCallback(EventListInteractorCallback())

        processor.setListComparator(EventListItemUiModelComparator.DateTimeComparator)
    }

    override fun onResume() {
        super.onResume()
        interactor.getEvents()
    }

    override fun getCurrentUserId(): String {
        return interactor.getCurrentUserId()
    }

    override fun onEventItemClick(eventId: String) {
        view?.showEventDetail(eventId)
    }

    override fun onFabClick() {
        view?.startCreateNewEvent()
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

    private inner class EventListInteractorCallback : EventListInteractor.EventListCallback {
        override fun onEventsRetrieved(entites: List<Event>) {
            processor.process(entites)
        }

        override fun onEventsRetrievalFailure() {
            view?.showLongToast(R.string.generic_error_loading_data)
        }
    }
}