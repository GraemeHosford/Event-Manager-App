package graeme.hosford.eventmanager.data.common.retrofit

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

abstract class BaseRetrofitService<Service> {

    protected val service: Service by lazy { createService() }

    protected abstract fun getServiceClass(): Class<Service>

    private fun createService(): Service {
        return Retrofit.Builder()
            .baseUrl("http://event-manager-fyp.herokuapp.com/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(getServiceClass())
    }

}