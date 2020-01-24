package graeme.hosford.eventmanager.presentation.company.join.presentation

import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.business.company.join.JoinCompanyInteractor
import graeme.hosford.eventmanager.presentation.company.join.JoinCompanyView
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.slot
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class JoinCompanyPresenterImplTest {

    private lateinit var presenter: JoinCompanyPresenterImpl

    @RelaxedMockK
    private lateinit var interactor: JoinCompanyInteractor

    @RelaxedMockK
    private lateinit var view: JoinCompanyView

    private val listenerCapture = slot<JoinCompanyInteractor.JoinCompanyListener>()

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        presenter = JoinCompanyPresenterImpl(interactor)
        presenter.onViewCreated(view)
        verify { interactor.registerCallback(capture(listenerCapture)) }
    }

    @Test
    fun onViewCreated_callsRegisterCallback_onInteractor() {
        presenter.onViewCreated(view)

        verify { interactor.registerCallback(listenerCapture.captured) }
    }

    @Test
    fun onJoinCompanyClick_callsInteractor_addUserToCompany() {
        presenter.onJoinCompanyClick("4")

        verify { interactor.addCurrentUserToCompany(4) }
    }

    @Test
    fun joinCompanyListener_onSuccess_callsShowMessageAndMainActivity() {
        listenerCapture.captured.onJoinCompanySuccess()

        verify { view.showLongToast(R.string.company_join_success_message) }
        verify { view.showMainActivity() }
    }

    @Test
    fun joinCompanyListener_onFailure_callsShowFailureMessage() {
        listenerCapture.captured.onJoinCompanyFailure()

        verify { view.showLongToast(R.string.company_join_failure_message) }
    }

}