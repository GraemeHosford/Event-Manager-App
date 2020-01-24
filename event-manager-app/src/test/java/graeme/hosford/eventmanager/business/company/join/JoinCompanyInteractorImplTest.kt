package graeme.hosford.eventmanager.business.company.join

import com.google.firebase.auth.FirebaseUser
import graeme.hosford.eventmanager.data.company.CompanyFirebaseAccess
import graeme.hosford.eventmanager.data.login.CurrentUserNetworkAccess
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.slot
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class JoinCompanyInteractorImplTest {

    private lateinit var interactor: JoinCompanyInteractorImpl

    @RelaxedMockK
    private lateinit var companyAccess: CompanyFirebaseAccess

    @RelaxedMockK
    private lateinit var userAccess: CurrentUserNetworkAccess

    @RelaxedMockK
    private lateinit var user: FirebaseUser

    private val addUserListener = slot<CompanyFirebaseAccess.AddUserListener>()

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        interactor = JoinCompanyInteractorImpl(companyAccess, userAccess)
        interactor.onCreate()

        verify { companyAccess.setAddUserListener(capture(addUserListener)) }
    }

    @Test
    fun onCreate_callsCompanyAccess_setAddUserListener() {
        interactor.onCreate()

        verify { companyAccess.setAddUserListener(addUserListener.captured) }
    }

    @Test
    fun addCurrentUserToCompany_callsCompanyAccess_addToCompany() {
        every { userAccess.getCurrentUser() } returns user
        every { user.email } returns "Test"

        interactor.addCurrentUserToCompany(3)

        verify { companyAccess.addUserToCompany(3, "Test") }
    }

}