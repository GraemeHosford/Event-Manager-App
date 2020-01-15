package graeme.hosford.eventmanager.presentation.login.presentation

import graeme.hosford.eventmanager.business.login.LoginInteractor
import graeme.hosford.eventmanager.presentation.login.LoginView
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class LoginPresenterImplTest {

    private lateinit var presenter: LoginPresenterImpl

    @RelaxedMockK
    private lateinit var view: LoginView

    @RelaxedMockK
    private lateinit var interactor: LoginInteractor

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        presenter = LoginPresenterImpl(interactor)
        presenter.onViewCreated(view)
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

}