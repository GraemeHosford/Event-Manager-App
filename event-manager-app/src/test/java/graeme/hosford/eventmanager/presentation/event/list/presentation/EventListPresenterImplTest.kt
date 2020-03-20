package graeme.hosford.eventmanager.presentation.event.list.presentation

import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.business.event.list.common.BaseEventListInteractor
import graeme.hosford.eventmanager.entity.event.Event
import graeme.hosford.eventmanager.presentation.common.model.UiModelListProcessor
import graeme.hosford.eventmanager.presentation.event.list.common.EventListView
import graeme.hosford.eventmanager.presentation.event.list.common.model.EventListItemUiModel
import graeme.hosford.eventmanager.presentation.event.list.common.model.EventListItemUiModelProcessor
import graeme.hosford.eventmanager.presentation.event.list.common.presentation.BaseEventListPresenterImpl
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.slot
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import java.util.*

class EventListPresenterImplTest {

    private lateinit var presenter: BaseEventListPresenterImpl

    @RelaxedMockK
    private lateinit var interactor: BaseEventListInteractor

    @RelaxedMockK
    private lateinit var processor: EventListItemUiModelProcessor

    @RelaxedMockK
    private lateinit var view: EventListView

    @RelaxedMockK
    private lateinit var startDate: Calendar

    @RelaxedMockK
    private lateinit var endDate: Calendar

    private val processorCapture =
        slot<UiModelListProcessor.ProcessingCompleteCallback<EventListItemUiModel>>()

    private val interactorCapture = slot<BaseEventListInteractor.EventListCallback>()

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        presenter = TestEventListPresenter()
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
            EventListItemUiModel(
                "122",
                "Graeme",
                "Event 1",
                "Desc 1",
                startDate,
                endDate,
                "Loc 1",
                arrayListOf()
            ),
            EventListItemUiModel(
                "123",
                "Graeme",
                "Event 2",
                "Desc 2",
                startDate,
                endDate,
                "Loc 2",
                arrayListOf()
            )
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
            Event(
                "122", "Graeme", "Event 1", "Desc 1", startDate,
                endDate, "Loc 1", 0.0, 0.0
            ),
            Event(
                "123", "Graeme", "Event 2", "Desc 2", startDate,
                endDate, "Loc 2", 0.0, 0.0
            )
        )

        interactorCapture.captured.onEventsRetrieved(entities)

        verify { processor.process(entities) }
    }

    @Test
    fun interactorCallback_onEventsRetrievedFailure_callsViewShowErrorMessage() {
        interactorCapture.captured.onEventsRetrievalFailure()

        verify { view.showLongToast(R.string.generic_error_loading_data) }
    }

    private inner class TestEventListPresenter : BaseEventListPresenterImpl(processor, interactor)

}