package graeme.hosford.eventmanager.presentation.login.presentation

import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.business.login.LoginInteractor
import graeme.hosford.eventmanager.business.user.CurrentUserInteractor
import graeme.hosford.eventmanager.presentation.login.LoginView
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.slot
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class LoginPresenterImplTest {

    private lateinit var presenter: LoginPresenterImpl

    @RelaxedMockK
    private lateinit var view: LoginView

    @RelaxedMockK
    private lateinit var interactor: LoginInteractor

    @RelaxedMockK
    private lateinit var userInteractor: CurrentUserInteractor

    private val userDetailsListener = slot<LoginInteractor.SaveUserDetailsListener>()

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        presenter = LoginPresenterImpl(interactor, userInteractor)
        presenter.onViewCreated(view)

        verify { interactor.registerCallback(capture(userDetailsListener)) }
    }

    @Test
    fun checkLoggedIn_showsCompanyCreationFlow_whenLoggedIn() {
        every { interactor.loggedIn() } returns true

        presenter.checkLoggedIn()

        verify { view.showCompanyCreationFlow() }
    }

    @Test
    fun checkLoggedIn_showsLoginFlow_whenNotLoggedIn() {
        every { interactor.loggedIn() } returns false

        presenter.checkLoggedIn()

        verify { view.showLoginFlow() }
    }

    @Test
    fun onUserDetailsSaveSuccess_showsCompanyCreationFlow() {
        userDetailsListener.captured.onSaveSuccess()

        verify { view.showCompanyCreationFlow() }
    }

    @Test
    fun onUserDetailsSaveFailure_showsErrorAndLoginFlow() {
        userDetailsListener.captured.onSaveFailure()

        verify { view.showLongToast(R.string.error_sign_in) }
        verify { view.showLoginFlow() }
    }

}