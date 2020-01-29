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

    private val saveCapture = slot<CurrentUserNetworkAccess.UserInfoSavedCallback>()

    private val retrieveCapture = slot<CurrentUserNetworkAccess.UserInfoRetrievedCallback>()

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        interactor = CurrentUserInteractorImpl(userNetwork)
        interactor.onCreate()

        verify { userNetwork.setUserInfoSavedListener(capture(saveCapture)) }
        verify { userNetwork.setUserInfoRetrievedListener(capture(retrieveCapture)) }
    }

    @Test
    fun onCreate_setsUserInfoListeners() {
        interactor.onCreate()

        verify { userNetwork.setUserInfoSavedListener(saveCapture.captured) }
        verify { userNetwork.setUserInfoRetrievedListener(retrieveCapture.captured) }
    }

    @Test
    fun setUserCompany_callsUserNetworkSetUserCompany() {
        every { userNetwork.getCurrentUser() } returns user
        every { user.email } returns "Test"

        interactor.setUserCompany("123")

        verify { userNetwork.saveUserInfo("Test", hashMapOf("companyId" to "123")) }
    }

    @Test
    fun checkUserHasCompany_callsNetworkAccess_getUserInfo() {
        every { userNetwork.getCurrentUser() } returns user
        every { user.email } returns "Test"

        interactor.checkUserHasCompany()

        verify { userNetwork.getUserInfo("Test", "companyId") }
    }

    @Test
    fun userCompanyListener_callsSuccessOnCallbackWhenSuccessful() {
        interactor.registerCallback(callback)

        saveCapture.captured.onUserInfoSavedSuccess()

        verify { callback.onAddUserCompanySuccess() }
    }

    @Test
    fun userCompanyListener_callsFailureOnCallbackWhenFailed() {
        interactor.registerCallback(callback)

        saveCapture.captured.onUserInfoSavedFailure()

        verify { callback.onAddUserCompanyFailure() }
    }

    @Test
    fun userInfoRetrieved_onUserInfoRetrieved_callsCallbackOnUserInfoRetrieved() {
        interactor.registerCallback(callback)

        retrieveCapture.captured.onUserInfoRetrieved("Some Info")
        verify { callback.onUserInfoRetrieved("Some Info") }
    }

    @Test
    fun userInfoRetrieved_onUserInfoRetrievedFailure_callsCallbackOnUserInfoRetrievedFailed() {
        interactor.registerCallback(callback)

        retrieveCapture.captured.onUserInfoRetrievalFailure()

        verify { callback.onUserInfoRetrievalFailed() }
    }

}