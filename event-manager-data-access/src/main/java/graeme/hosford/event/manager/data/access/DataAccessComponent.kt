package graeme.hosford.event.manager.data.access

import java.util.concurrent.ExecutorService
import javax.inject.Named

/**
 * Interface that lists all public 'data access' layer components which need to be exposed to the `business` layer.
 *
 * When Dagger is used, by letting the Dagger Component from the 'data layer' extend this interface we declare
 * [provision methods](https://dagger.dev/api/2.25.2/dagger/Component.html) which then can be used by injected
 * classes in the business layer.
 */
interface DataAccessComponent {

    companion object {
        /**
         * The singleton instance for [DataAccessComponent].
         * This is initialised by the `data-bridge` module and used by the `business` layer.
         * The instance can be replaced with a mock for testing when necessary.
         */
        @Volatile
        @JvmStatic
        lateinit var INSTANCE: DataAccessComponent

        const val GLOBAL_COMPUTATION_EXECUTOR = "computation_executor"
        const val GLOBAL_IO_EXECUTOR = "IO_executor"
    }

    @Named(GLOBAL_IO_EXECUTOR)
    fun ioExecutor(): ExecutorService

    @Named(GLOBAL_COMPUTATION_EXECUTOR)
    fun computationExecutor(): ExecutorService

}