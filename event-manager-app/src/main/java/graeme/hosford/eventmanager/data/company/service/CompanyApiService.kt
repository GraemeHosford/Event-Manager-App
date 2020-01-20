package graeme.hosford.eventmanager.data.company.service

import graeme.hosford.eventmanager.entity.company.Company
import io.reactivex.Observable
import retrofit2.http.POST
import retrofit2.http.Path

interface CompanyApiService {

    @POST("api/V1/company/{name}")
    fun getCompanyId(@Path("name") name: String): Observable<Company>

}