package graeme.hosford.event.manager.data.access

import graeme.hosford.event.manager.entity.BusinessEntity

@Suppress("unused")
/**
 * Basic interface for an asynchronous data result.
 */
interface DataCallback<R : BusinessEntity> {

    fun onSuccess(result: R)

    fun onError(exception: Exception)

}