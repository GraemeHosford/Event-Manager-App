package graeme.hosford.eventmanager.business.company.create

import graeme.hosford.eventmanager.business.user.CurrentUserInteractor
import graeme.hosford.eventmanager.data.company.CompanyFirebaseAccess
import graeme.hosford.eventmanager.data.company.CompanyFirebaseAccessImpl
import graeme.hosford.eventmanager.data.company.service.CompanyApiService
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.slot
import io.mockk.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CreateCompanyInteractorImplTest {

    private lateinit var interactor: CreateCompanyInteractorImpl

    @RelaxedMockK
    private lateinit var firebaseAccess: CompanyFirebaseAccessImpl

    @RelaxedMockK
    private lateinit var companyApiService: CompanyApiService

    @RelaxedMockK
    private lateinit var currentUserInteractor: CurrentUserInteractor

    @RelaxedMockK
    private lateinit var currentUserCallback: CurrentUserInteractor.UserCompanyListener

    @RelaxedMockK
    private lateinit var createCompanyListener: CreateCompanyInteractor.CreateCompanyListener

    private val firebaseListener = slot<CompanyFirebaseAccess.CompanySaveListener>()

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        interactor =
            CreateCompanyInteractorImpl(firebaseAccess, companyApiService, currentUserInteractor)
        interactor.onCreate()

        verify { firebaseAccess.setCompanySaveListener(capture(firebaseListener)) }
    }

    @Test
    fun registerCurrentUserInteractorListener_callsCurrentUserInteractorRegisterCallback() {
        interactor.registerCurrentUserInteractorListener(currentUserCallback)

        verify { currentUserInteractor.registerCallback(currentUserCallback) }
    }

    @Test
    fun registerManagedInteractors_registersCurrentUserInteractor() {
        val interactors = interactor.registerManagedInteractors()

        Assert.assertEquals(listOf(currentUserInteractor), interactors)
    }

    @Test
    fun setUserCompany_callsUserInteractorSaveCompany() {
        interactor.setUserCompany("123")

        verify { currentUserInteractor.setUserCompany("123") }
    }

    @Test
    fun saveCompany_callsCompanyAccessSaveCompany() {
        interactor.saveCompany(1, "Test")

        verify { firebaseAccess.saveCompany(1, "Test") }
    }

    @Test
    fun companySaveListener_callsInteractorListenerOnSaveCompanySuccess_whenSaveSuccessful() {
        interactor.registerCallback(createCompanyListener)

        firebaseListener.captured.onCompanySaveSuccess("123")
        verify { createCompanyListener.onSaveCompanySuccess("123") }
    }

    @Test
    fun companySaveListener_callsInteractorListenerOnSaveCompanyFailure_whenSaveFails() {
        interactor.registerCallback(createCompanyListener)

        firebaseListener.captured.onCompanySaveFailure()
        verify { createCompanyListener.onSaveCompanyFailure() }
    }

}