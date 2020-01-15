package graeme.hosford.eventmanager.business.login

import com.google.firebase.auth.FirebaseUser
import graeme.hosford.eventmanager.data.login.CurrentUserNetworkAccess
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class LoginInteractorImplTest {

    private lateinit var interactor: LoginInteractorImpl

    @RelaxedMockK
    private lateinit var userAccess: CurrentUserNetworkAccess

    @RelaxedMockK
    private lateinit var user: FirebaseUser

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        interactor = LoginInteractorImpl(userAccess)
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

}