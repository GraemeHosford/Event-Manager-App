package graeme.hosford.eventmanager.business.login

import com.google.firebase.auth.FirebaseUser
import graeme.hosford.eventmanager.data.login.CurrentUserNetworkAccess
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.slot
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class LoginInteractorImplTest {

    private lateinit var interactor: LoginInteractorImpl

    @RelaxedMockK
    private lateinit var userAccess: CurrentUserNetworkAccess

    @RelaxedMockK
    private lateinit var interactorListener: LoginInteractor.SaveUserDetailsListener

    @RelaxedMockK
    private lateinit var user: FirebaseUser

    private val saveListener = slot<CurrentUserNetworkAccess.EmailSaveListener>()

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        interactor = LoginInteractorImpl(userAccess)
        interactor.onCreate()

        verify { userAccess.setEmailSaveListener(capture(saveListener)) }
    }

    @Test
    fun loggedIn_returnsTrue_whenUserNotNull() {
        every { userAccess.getCurrentUser() } returns user

        assertEquals(true, interactor.loggedIn())
    }

    @Test
    fun loggedIn_returnsFalse_whenUserIsNull() {
        every { userAccess.getCurrentUser() } returns null

        assertEquals(false, interactor.loggedIn())
    }

    @Test
    fun saveUserDetails_callsUserAccessSaveInfoWHenEmailNotNull() {
        interactor.saveUserDetails("Test")

        verify { userAccess.saveUserInfo("Test") }
    }

    @Test
    fun saveUserDetails_callsUserDetailsListenerOnFailureWhenEmailIsNull() {
        interactor.setUserDetailsListener(interactorListener)
        interactor.saveUserDetails(null)

        verify { interactorListener.onSaveFailure() }
    }

    @Test
    fun emailSaveListener_callsUserDetailsListenerOnSaveSuccessWhenSavedSuccessfully() {
        interactor.setUserDetailsListener(interactorListener)
        saveListener.captured.onEmailSaveSuccess()

        verify { interactorListener.onSaveSuccess() }
    }

    @Test
    fun emailSaveListener_callsUserDetailsListenerOnSaveFailureWhenNotSaved() {
        interactor.setUserDetailsListener(interactorListener)
        saveListener.captured.onEmailSaveFailure()

        verify { interactorListener.onSaveFailure() }
    }

}