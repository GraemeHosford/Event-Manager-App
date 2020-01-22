package graeme.hosford.eventmanager.data.company.service

import graeme.hosford.eventmanager.data.common.retrofit.BaseRetrofitService
import graeme.hosford.eventmanager.entity.company.Company
import io.reactivex.Observable
import javax.inject.Inject

class CompanyApiServiceImpl @Inject constructor() :
    BaseRetrofitService<CompanyApiService>(), CompanyApiService {

    override fun getServiceClass(): Class<CompanyApiService> = CompanyApiService::class.java

    override fun getCompanyId(name: String): Observable<Company> {
        return service.getCompanyId(name)
    }
}