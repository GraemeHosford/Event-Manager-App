package graeme.hosford.eventmanager.data.company.service

import graeme.hosford.eventmanager.entity.company.Company
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

class CompanyApiServiceImpl @Inject constructor() :
    CompanyApiService {

    private val service: CompanyApiService by lazy { createService() }

    private fun createService(): CompanyApiService {
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(CompanyApiService::class.java)
    }

    override fun getCompanyId(name: String): Observable<Company> {
        return service.getCompanyId(name)
    }
}