package graeme.hosford.eventmanager.presentation.company.join.presentation

import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.business.company.join.JoinCompanyInteractor
import graeme.hosford.eventmanager.business.user.CurrentUserInteractor
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
    private lateinit var userInteractor: CurrentUserInteractor

    @RelaxedMockK
    private lateinit var view: JoinCompanyView

    private val listenerCapture = slot<JoinCompanyInteractor.JoinCompanyListener>()

    private val userListenerCapture = slot<CurrentUserInteractor.UserCompanyListener>()

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        presenter = JoinCompanyPresenterImpl(interactor, userInteractor)
        presenter.onViewCreated(view)
        verify { interactor.registerCallback(capture(listenerCapture)) }
        verify { userInteractor.registerCallback(capture(userListenerCapture)) }
    }

    @Test
    fun onViewCreated_callsRegisterCallback_onInteractor() {
        presenter.onViewCreated(view)

        verify { interactor.registerCallback(listenerCapture.captured) }
    }

    @Test
    fun onJoinCompanyClick_callsInteractor_addUserToCompany() {
        presenter.onJoinCompanyClick("4")

        verify { interactor.addCurrentUserToCompany("4") }
    }

    @Test
    fun joinCompanyListener_onSuccess_callsShowMessageAndMainActivity() {
        listenerCapture.captured.onJoinCompanySuccess("4")

        verify { userInteractor.setUserCompany("4") }
    }

    @Test
    fun joinCompanyListener_onFailure_callsShowFailureMessage() {
        listenerCapture.captured.onJoinCompanyFailure()

        verify { view.showLongToast(R.string.company_join_failure_message) }
    }

    @Test
    fun addUserListener_onSuccess_showsMainActivity() {
        userListenerCapture.captured.onAddUserCompanySuccess()

        verify { view.showMainActivity() }
    }

    @Test
    fun addUserListener_onFailure_callsViewShowErrorMessage() {
        userListenerCapture.captured.onAddUserCompanyFailure()

        verify { view.showLongToast(R.string.company_join_failure_message) }
    }

}