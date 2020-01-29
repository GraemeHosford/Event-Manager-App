package graeme.hosford.eventmanager.presentation.event.list.presentation

import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.business.event.list.EventListInteractor
import graeme.hosford.eventmanager.entity.event.Event
import graeme.hosford.eventmanager.presentation.common.model.UiModelListProcessor
import graeme.hosford.eventmanager.presentation.event.list.EventListView
import graeme.hosford.eventmanager.presentation.event.list.model.EventListItemUiModel
import graeme.hosford.eventmanager.presentation.event.list.model.EventListItemUiModelProcessor
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.slot
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class EventListPresenterImplTest {

    private lateinit var presenter: EventListPresenterImpl

    @RelaxedMockK
    private lateinit var interactor: EventListInteractor

    @RelaxedMockK
    private lateinit var processor: EventListItemUiModelProcessor

    @RelaxedMockK
    private lateinit var view: EventListView

    private val processorCapture =
        slot<UiModelListProcessor.ProcessingCompleteCallback<EventListItemUiModel>>()

    private val interactorCapture = slot<EventListInteractor.EventListCallback>()

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        presenter = EventListPresenterImpl(processor, interactor)
        presenter.onViewCreated(view)

        verify { processor.registerProcessingCallback(capture(processorCapture)) }
        verify { interactor.registerCallback(capture(interactorCapture)) }
    }

    @Test
    fun onViewCreated_callsProcessor_registerProcessingCallback() {
        presenter.onViewCreated(view)

        verify { processor.registerProcessingCallback(processorCapture.captured) }
    }

    @Test
    fun onViewCreated_callsInteractor_registerCallback() {
        presenter.onViewCreated(view)

        verify { interactor.registerCallback(interactorCapture.captured) }
    }

    @Test
    fun eventListProcessingCallback_showsData_onSuccess() {
        val models = listOf(
            EventListItemUiModel("Event 1"),
            EventListItemUiModel("Event 2")
        )

        processorCapture.captured.onProcessingComplete(models)

        verify { view.showData(models) }
    }

    @Test
    fun eventListProcessingCallback_showsErrorMessage_onFailure() {
        processorCapture.captured.onProcessingFailure()

        verify { view.showLongToast(R.string.generic_error_loading_data) }
    }

    @Test
    fun interactorCallback_onEventsRetrieved_callsProcessorToProcess() {
        val entities = listOf(
            Event("Event 1"),
            Event("Event 2")
        )

        interactorCapture.captured.onEventsRetrieved(entities)

        verify { processor.process(entities) }
    }

    @Test
    fun interactorCallback_onEventsRetrievedFailure_callsViewShowErrorMessage() {
        interactorCapture.captured.onEventsRetrievalFailure()

        verify { view.showLongToast(R.string.generic_error_loading_data) }
    }

}