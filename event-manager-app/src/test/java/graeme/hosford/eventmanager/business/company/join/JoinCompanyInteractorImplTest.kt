package graeme.hosford.eventmanager.business.company.join

import com.google.firebase.auth.FirebaseUser
import graeme.hosford.eventmanager.business.user.CurrentUserInteractor
import graeme.hosford.eventmanager.data.company.CompanyFirebaseAccess
import graeme.hosford.eventmanager.data.login.CurrentUserNetworkAccess
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.slot
import io.mockk.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class JoinCompanyInteractorImplTest {

    private lateinit var interactor: JoinCompanyInteractorImpl

    @RelaxedMockK
    private lateinit var companyAccess: CompanyFirebaseAccess

    @RelaxedMockK
    private lateinit var userAccess: CurrentUserNetworkAccess

    @RelaxedMockK
    private lateinit var currentUserInteractor: CurrentUserInteractor

    @RelaxedMockK
    private lateinit var userListener: CurrentUserInteractor.UserCompanyListener

    @RelaxedMockK
    private lateinit var user: FirebaseUser

    private val addUserListener = slot<CompanyFirebaseAccess.AddUserListener>()

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        interactor = JoinCompanyInteractorImpl(companyAccess, userAccess, currentUserInteractor)
        interactor.onCreate()

        verify { companyAccess.setAddUserListener(capture(addUserListener)) }
    }

    @Test
    fun onCreate_callsCompanyAccess_setAddUserListener() {
        interactor.onCreate()

        verify { companyAccess.setAddUserListener(addUserListener.captured) }
    }

    @Test
    fun setCurrentUserInteractorCallback_callsRegisterCallbackOnCurrentUserInteractor() {
        interactor.registerCurrentUserInteractorCallback(userListener)

        verify { currentUserInteractor.registerCallback(userListener) }
    }

    @Test
    fun registerManagedInteractors_registersCurrentUserInteractor() {
        val interactors = interactor.registerManagedInteractors()

        Assert.assertEquals(listOf(currentUserInteractor), interactors)
    }

    @Test
    fun setUserComapny_callsCurrentUserInteractors_setUserCompany() {
        interactor.setUserCompany("2")

        verify { currentUserInteractor.setUserCompany("2") }
    }

    @Test
    fun addCurrentUserToCompany_callsCompanyAccess_addToCompany() {
        every { userAccess.getCurrentUser() } returns user
        every { user.email } returns "Test"

        interactor.addCurrentUserToCompany("3")

        verify { companyAccess.addUserToCompany("3", "Test") }
    }

}