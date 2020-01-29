package graeme.hosford.eventmanager.data.company.service

import graeme.hosford.eventmanager.entity.company.Company
import io.reactivex.Observable
import retrofit2.http.POST
import retrofit2.http.Query

interface CompanyApiService {

    @POST("companys")
    fun getCompanyId(@Query("name") name: String): Observable<Company>

}