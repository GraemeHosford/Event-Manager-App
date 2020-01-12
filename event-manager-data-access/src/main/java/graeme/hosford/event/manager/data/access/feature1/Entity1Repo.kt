package graeme.hosford.event.manager.data.access.feature1

import graeme.hosford.event.manager.data.access.DataRepo
import graeme.hosford.event.manager.data.access.DataRequest
import graeme.hosford.event.manager.entity.feature1.Entity1
import graeme.hosford.event.manager.entity.feature1.Entity1List

interface Entity1Repo : DataRepo {

    fun initialize()

    fun getEntity1(id: Long): DataRequest<Entity1>

    fun getEntity1List(): DataRequest<Entity1List>

}