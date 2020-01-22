package graeme.hosford.eventmanager.data.company.service

import graeme.hosford.eventmanager.entity.company.Company
import io.reactivex.Observable
import retrofit2.http.POST
import retrofit2.http.Query

interface CompanyApiService {

    companion object {
        private const val BASE_COMPANY_API_URL = "event-manager-fyp.herokuapp.com/companys"
    }

    @POST(BASE_COMPANY_API_URL)
    fun getCompanyId(@Query("name") name: String): Observable<Company>

}