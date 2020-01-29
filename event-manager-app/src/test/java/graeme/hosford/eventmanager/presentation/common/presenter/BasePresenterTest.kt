package graeme.hosford.eventmanager.presentation.common.presenter

import graeme.hosford.eventmanager.business.login.LoginInteractor
import graeme.hosford.eventmanager.business.login.LoginInteractorImpl
import graeme.hosford.eventmanager.presentation.login.LoginView
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class BasePresenterTest {

    private lateinit var presenter: TestBasePresenter

    @RelaxedMockK
    private lateinit var testInteractor: LoginInteractorImpl

    @RelaxedMockK
    private lateinit var view: LoginView

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        presenter = TestBasePresenter(testInteractor)
        presenter.onViewCreated(view)
    }

    @Test
    fun onViewCreated_callsInteractorOnCreate() {
        presenter.onViewCreated(view)

        verify { testInteractor.onCreate() }
    }

    /* Using any View and Interactor as this class is for testing common functionality.
    Exact view and interactor therefore should not matter */
    private inner class TestBasePresenter(interactor: LoginInteractor) :
        BasePresenter<LoginView, LoginInteractor>(interactor)

}