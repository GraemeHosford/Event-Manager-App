package graeme.hosford.event.manager.data.access.feature2

import graeme.hosford.event.manager.data.access.DataRepo
import graeme.hosford.event.manager.data.access.DataRequest
import graeme.hosford.event.manager.entity.feature2.Entity2

interface Entity2Repo : DataRepo {

    fun getEntity2(id: Long): DataRequest<Entity2>

}