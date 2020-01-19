package graeme.hosford.eventmanager.business.company.create

import graeme.hosford.eventmanager.data.company.CompanyFirebaseAccess
import graeme.hosford.eventmanager.data.company.CompanyFirebaseAccessImpl
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.slot
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class CreateCompanyInteractorImplTest {

    private lateinit var interactor: CreateCompanyInteractorImpl

    @RelaxedMockK
    private lateinit var firebaseAccess: CompanyFirebaseAccessImpl

    @RelaxedMockK
    private lateinit var createCompanyListener: CreateCompanyInteractor.CreateCompanyListener

    private val firebaseListener = slot<CompanyFirebaseAccess.CompanySaveListener>()

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        interactor = CreateCompanyInteractorImpl(firebaseAccess)
        interactor.onCreate()

        verify { firebaseAccess.setCompanySaveListener(capture(firebaseListener)) }
    }

    @Test
    fun saveCompany_callsCompanyAccessSaveCompany() {
        interactor.saveCompany(1, "Test")

        verify { firebaseAccess.saveCompany(1, "Test") }
    }

    @Test
    fun companySaveListener_callsInteractorListenerOnSaveCompanySuccess_whenSaveSuccessful() {
        interactor.registerCallback(createCompanyListener)

        firebaseListener.captured.onCompanySaveSuccess()
        verify { createCompanyListener.onSaveCompanySuccess() }
    }

    @Test
    fun companySaveListener_callsInteractorListenerOnSaveCompanyFailure_whenSaveFails() {
        interactor.registerCallback(createCompanyListener)

        firebaseListener.captured.onCompanySaveFailure()
        verify { createCompanyListener.onSaveCompanyFailure() }
    }

}