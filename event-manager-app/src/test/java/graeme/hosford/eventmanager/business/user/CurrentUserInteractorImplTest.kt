package graeme.hosford.eventmanager.business.user

import com.google.firebase.auth.FirebaseUser
import graeme.hosford.eventmanager.data.login.CurrentUserNetworkAccess
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.slot
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class CurrentUserInteractorImplTest {

    private lateinit var interactor: CurrentUserInteractorImpl

    @RelaxedMockK
    private lateinit var userNetwork: CurrentUserNetworkAccess

    @RelaxedMockK
    private lateinit var callback: CurrentUserInteractor.UserCompanyListener

    @RelaxedMockK
    private lateinit var user: FirebaseUser

    private val capture = slot<CurrentUserNetworkAccess.UserInfoSavedCallback>()

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        interactor = CurrentUserInteractorImpl(userNetwork)
        interactor.onCreate()

        verify { userNetwork.setUserInfoSavedListener(capture(capture)) }
    }

    @Test
    fun onCreate_addUserCompanyListener() {
        interactor.onCreate()

        verify { userNetwork.setUserInfoSavedListener(capture.captured) }
    }

    @Test
    fun setUserCompany_callsUserNetworkSetUserCompany() {
        every { userNetwork.getCurrentUser() } returns user
        every { user.email } returns "Test"

        interactor.setUserCompany("123")

        verify { userNetwork.saveUserInfo("Test", hashMapOf("companyId" to "123")) }
    }

    @Test
    fun userCompanyListener_callsSuccessOnCallbackWhenSuccessful() {
        interactor.registerCallback(callback)

        capture.captured.onUserInfoSavedSuccess()

        verify { interactor.callback?.onAddUserCompanySuccess() }
    }

    @Test
    fun userCompanyListener_callsFailureOnCallbackWhenFailed() {
        interactor.registerCallback(callback)

        capture.captured.onUserInfoSavedFailure()

        verify { interactor.callback?.onAddUserCompanyFailure() }
    }

}