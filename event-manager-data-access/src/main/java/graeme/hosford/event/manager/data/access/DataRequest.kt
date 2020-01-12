package graeme.hosford.event.manager.data.access

import graeme.hosford.event.manager.entity.BusinessEntity
import java.io.IOException

/**
 * Basic interface for a component for users of a [DataRepo] that want to execute, synchonously or asynchronously, a
 * data request.
 */
interface DataRequest<R : BusinessEntity> {

    @Throws(IOException::class)
    fun sync(): R

    fun async(callback: DataCallback<R>)

}