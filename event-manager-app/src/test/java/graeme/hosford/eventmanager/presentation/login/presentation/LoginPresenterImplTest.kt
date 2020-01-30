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

    private val userDetailsListener = slot<LoginInteractor.SaveUserDetailsListener>()

    private val userInfoRetrieved = slot<CurrentUserInteractor.UserCompanyListener>()

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        presenter = LoginPresenterImpl(interactor)
        presenter.onViewCreated(view)

        verify { interactor.registerCallback(capture(userDetailsListener)) }
        verify { interactor.registerCurrentUserInteractorCallback(capture(userInfoRetrieved)) }
    }

    @Test
    fun checkLoggedIn_checksIfUserAlreadyHasCompany_whenLoggedIn() {
        every { interactor.loggedIn() } returns true

        presenter.checkLoggedIn()

        verify { interactor.checkUserHasCompany() }
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

        verify { interactor.checkUserHasCompany() }
    }

    @Test
    fun onUserDetailsSaveFailure_showsErrorAndLoginFlow() {
        userDetailsListener.captured.onSaveFailure()

        verify { view.showLongToast(R.string.error_sign_in) }
        verify { view.showLoginFlow() }
    }

    @Test
    fun userInfoRetrieved_onAddUserCompanySuccess_checksUserHasCompany() {
        userInfoRetrieved.captured.onAddUserCompanySuccess()

        verify { interactor.checkUserHasCompany() }
    }

    @Test
    fun userInfoRetrieved_onAddUserCompanyFailure_showsErrorAndLoginFlow() {
        userInfoRetrieved.captured.onAddUserCompanyFailure()

        verify { view.showLongToast(R.string.error_sign_in) }
        verify { view.showLoginFlow() }
    }

    @Test
    fun userInfoRetrieved_onInfoRetrieved_showsMainActivity_whenNotNull() {
        userInfoRetrieved.captured.onUserInfoRetrieved("123")

        verify { view.showMainActivity() }
    }

    @Test
    fun userInfoRetrieved_onInfoRetrieved_showsCompanyCreationFlow_whenNull() {
        userInfoRetrieved.captured.onUserInfoRetrieved(null)

        verify { view.showCompanyCreationFlow() }
    }

    @Test
    fun userInfoRetrieved_onInfoRetrievedFailure_showsError() {
        userInfoRetrieved.captured.onUserInfoRetrievalFailed()

        verify { view.showLongToast(R.string.generic_error_loading_data) }
    }
}