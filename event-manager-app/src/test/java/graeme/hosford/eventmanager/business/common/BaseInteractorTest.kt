package graeme.hosford.eventmanager.business.common

import graeme.hosford.eventmanager.business.login.LoginInteractor
import graeme.hosford.eventmanager.business.user.CurrentUserInteractor
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class BaseInteractorTest {

    private lateinit var interactor: TestInteractor

    @RelaxedMockK
    private lateinit var managedInteractor1: CurrentUserInteractor

    @RelaxedMockK
    private lateinit var managedInteractor2: LoginInteractor

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        interactor = TestInteractor()
    }

    @Test
    fun onCreate_callsRegisterManagedInteractors() {
        interactor.onCreate()

        verify { managedInteractor1.onCreate() }
        verify { managedInteractor2.onCreate() }
    }

    /* Only testing base common functionality so the callback being used is arbitrary */
    private inner class TestInteractor :
        BaseInteractor<LoginInteractor.SaveUserDetailsListener>() {

        override fun registerManagedInteractors(): List<Interactor<*>> =
            listOf(
                /* Arbitrary interactors being returned just to test that
                onCreate is called for all of them */
                managedInteractor1,
                managedInteractor2
            )
    }
}