package graeme.hosford.eventmanager.data.common.retrofit

import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

abstract class BaseRetrofitService<Service> {

    protected val service: Service by lazy { createService() }

    protected abstract fun getServiceClass(): Class<Service>

    private fun createService(): Service {
        return Retrofit.Builder()
            .baseUrl("http://event-manager-fyp.herokuapp.com")
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(getServiceClass())
    }

}