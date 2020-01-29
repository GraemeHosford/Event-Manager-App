package graeme.hosford.eventmanager.data.injection.module.internal

import dagger.Module
import java.util.concurrent.ExecutorService

/**
 * [Module] that provide singleton [ExecutorService] for thread pools that will be reused across the application.
 *
 * This could also include custom RxJava `Scheduler`s or any other global, cross-layer threading dependency.
 */
@Module
internal interface ThreadingModule