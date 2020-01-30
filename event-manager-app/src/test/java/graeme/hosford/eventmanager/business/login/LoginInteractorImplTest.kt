package graeme.hosford.eventmanager.business.login

import com.google.firebase.auth.FirebaseUser
import graeme.hosford.eventmanager.business.user.CurrentUserInteractor
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
    private lateinit var currentUserInteractor: CurrentUserInteractor

    @RelaxedMockK
    private lateinit var userlistener: CurrentUserInteractor.UserCompanyListener

    @RelaxedMockK
    private lateinit var interactorListener: LoginInteractor.SaveUserDetailsListener

    @RelaxedMockK
    private lateinit var user: FirebaseUser

    private val saveListener = slot<CurrentUserNetworkAccess.UserInfoSavedCallback>()

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        interactor = LoginInteractorImpl(userAccess, currentUserInteractor)
        interactor.onCreate()

        verify { userAccess.setUserInfoSavedListener(capture(saveListener)) }
    }

    @Test
    fun registerManagedInteractors_registersCurrentUserInteractor() {
        assertEquals(listOf(currentUserInteractor), interactor.registerManagedInteractors())
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
    fun registerCurrentUserInteractorCallback_callsCurrentUserInteractorRegisterCallback() {
        interactor.registerCurrentUserInteractorCallback(userlistener)

        verify { currentUserInteractor.registerCallback(userlistener) }
    }

    @Test
    fun checkUserHasCompany_callsCurrentUserInteractorCheckUserHasCompany() {
        interactor.checkUserHasCompany()

        verify { currentUserInteractor.checkUserHasCompany() }
    }

    @Test
    fun saveUserDetails_callsUserAccessSaveInfoWHenEmailNotNull() {
        interactor.saveUserEmail("Test")

        verify { userAccess.saveUserInfo("Test", hashMapOf("userEmail" to "Test")) }
    }

    @Test
    fun saveUserDetails_callsUserDetailsListenerOnFailureWhenEmailIsNull() {
        interactor.registerCallback(interactorListener)
        interactor.saveUserEmail(null)

        verify { interactorListener.onSaveFailure() }
    }

    @Test
    fun emailSaveListener_callsUserDetailsListenerOnSaveSuccessWhenSavedSuccessfully() {
        interactor.registerCallback(interactorListener)
        saveListener.captured.onUserInfoSavedSuccess()

        verify { interactorListener.onSaveSuccess() }
    }

    @Test
    fun emailSaveListener_callsUserDetailsListenerOnSaveFailureWhenNotSaved() {
        interactor.registerCallback(interactorListener)
        saveListener.captured.onUserInfoSavedFailure()

        verify { interactorListener.onSaveFailure() }
    }

}