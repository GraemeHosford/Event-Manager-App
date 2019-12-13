package graeme.hosford.event.manager.data.access

import java.io.IOException

/**
 * Basic interface for a component for users of a [DataRepo] that want to execute, synchonously or asynchronously, a
 * data request.
 */
interface DataRequest<R : BusinessEntity> {

    @WorkerThread
    @Throws(IOException::class)
    fun sync(): R

    @AnyThread
    fun async(callback: DataCallback<R>)

}