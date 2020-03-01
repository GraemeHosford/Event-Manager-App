package graeme.hosford.eventmanager.business.event.list

import com.google.firebase.auth.FirebaseUser
import graeme.hosford.eventmanager.business.event.list.common.BaseEventListInteractor
import graeme.hosford.eventmanager.business.event.list.common.BaseEventListInteractorImpl
import graeme.hosford.eventmanager.data.event.list.EventListFirebaseAccess
import graeme.hosford.eventmanager.data.login.CurrentUserNetworkAccess
import graeme.hosford.eventmanager.entity.event.Event
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.slot
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import java.util.*

class EventListInteractorImplTest {

    private lateinit var interactor: BaseEventListInteractorImpl

    @RelaxedMockK
    private lateinit var eventListFirebaseAccess: EventListFirebaseAccess

    @RelaxedMockK
    private lateinit var currentUserNetworkAccess: CurrentUserNetworkAccess

    @RelaxedMockK
    private lateinit var callback: BaseEventListInteractor.EventListCallback

    @RelaxedMockK
    private lateinit var user: FirebaseUser

    private val eventListenerCapture = slot<EventListFirebaseAccess.EventDataListener>()

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        interactor = TestEventListInteractor()
        interactor.onCreate()

        verify { eventListFirebaseAccess.setEventListener(capture(eventListenerCapture)) }
    }

    @Test
    fun onCreate_setsEventListener() {
        interactor.onCreate()

        verify { eventListFirebaseAccess.setEventListener(eventListenerCapture.captured) }
    }

    @Test
    fun getEvents_callsEventListFirebaseAccess_getEvents() {
        every { currentUserNetworkAccess.getCurrentUser() } returns user
        every { user.email } returns "TestEmail"

        interactor.getEvents()

        verify { eventListFirebaseAccess.getAllEvents("TestEmail") }
    }

    @Test
    fun eventsListener_onEventsRetrievedSuccess_callsOnEventsRetrievedCallback() {
        interactor.registerCallback(callback)

        val cal = Calendar.getInstance()

        val entities = listOf(
            Event("123", "Graeme", "Event 1", "Desc", cal, cal, "Loc")
        )

        eventListenerCapture.captured.onEventRetrieveSuccess(entities)

        verify { interactor.callback?.onEventsRetrieved(entities) }
    }

    @Test
    fun eventsListener_onEventsRetrievedFailure_callsOnEventRetrievedFailureCallback() {
        interactor.registerCallback(callback)

        eventListenerCapture.captured.onEventRetrieveFailure()

        verify { interactor.callback?.onEventsRetrievalFailure() }
    }

    private inner class TestEventListInteractor :
        BaseEventListInteractorImpl(eventListFirebaseAccess, currentUserNetworkAccess)

}