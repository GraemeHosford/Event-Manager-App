package graeme.hosford.eventmanager.data.injection.module.internal

import dagger.Binds
import dagger.Module
import graeme.hosford.eventmanager.data.company.service.CompanyApiService
import graeme.hosford.eventmanager.data.company.service.CompanyApiServiceImpl
import javax.inject.Singleton

/**
 * Module that provides singleton components for networking dependencies and API.
 */
@Module
internal interface NetworkModule {

    @Binds
    @Singleton
    fun companyApiService(impl: CompanyApiServiceImpl): CompanyApiService

}