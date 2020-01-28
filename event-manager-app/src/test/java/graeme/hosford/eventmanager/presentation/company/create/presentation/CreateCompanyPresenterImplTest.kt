package graeme.hosford.eventmanager.presentation.company.create.presentation

import graeme.hosford.eventmanager.R
import graeme.hosford.eventmanager.business.company.create.CreateCompanyInteractor
import graeme.hosford.eventmanager.business.company.create.CreateCompanyInteractorImpl
import graeme.hosford.eventmanager.business.user.CurrentUserInteractor
import graeme.hosford.eventmanager.presentation.company.create.CreateCompanyView
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.slot
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class CreateCompanyPresenterImplTest {

    private lateinit var presenter: CreateCompanyPresenterImpl

    @RelaxedMockK
    private lateinit var view: CreateCompanyView

    @RelaxedMockK
    private lateinit var interactor: CreateCompanyInteractorImpl

    @RelaxedMockK
    private lateinit var userInteractor: CurrentUserInteractor

    private val listener = slot<CreateCompanyInteractor.CreateCompanyListener>()

    private val interactorCallback = slot<CurrentUserInteractor.UserCompanyListener>()

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        presenter = CreateCompanyPresenterImpl(interactor, userInteractor)
        presenter.onViewCreated(view)

        verify { interactor.registerCallback(capture(listener)) }
        verify { userInteractor.registerCallback(capture(interactorCallback)) }
    }

    @Test
    fun onCreateCompanyButtonClick_callsInteractorGetCompanyId() {
        presenter.onCreateCompanyButtonClick("Test")

        verify { interactor.getCompanyId("Test") }
    }

    @Test
    fun companyListener_callsInteractorSaveCompany_onGetCompanyIdSuccess() {
        listener.captured.onGetCompanyIdSuccess(1, "Test")

        verify { interactor.saveCompany(1, "Test") }
    }

    @Test
    fun companyListener_showsErrorMessage_onGetCompanyIdFailure() {
        listener.captured.onGetCompanyIdFailure()

        verify { view.showLongToast(R.string.error_creating_company) }
    }

    @Test
    fun companyListener_showsEventList_onSaveCompanySuccess() {
        listener.captured.onSaveCompanySuccess("123")

        verify { userInteractor.setUserCompany("123") }
    }

    @Test
    fun companyListener_showsErrorMessage_onSaveCompanyFailure() {
        listener.captured.onSaveCompanyFailure()

        verify { view.showLongToast(R.string.error_creating_company) }
    }

    @Test
    fun interactorCallback_SuccessShowsMainActivity() {
        interactorCallback.captured.onAddUserCompanySuccess()

        verify { view.showMainActivity() }
    }

    @Test
    fun interactorCallback_FailureShowsErrorMessage() {
        interactorCallback.captured.onAddUserCompanyFailure()

        verify { view.showLongToast(R.string.error_creating_company) }
    }

}