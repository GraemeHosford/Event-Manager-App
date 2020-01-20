package graeme.hosford.eventmanager.business.company.create

import graeme.hosford.eventmanager.business.common.BaseInteractor
import graeme.hosford.eventmanager.data.company.CompanyFirebaseAccess
import graeme.hosford.eventmanager.data.company.CompanyFirebaseAccessImpl
import graeme.hosford.eventmanager.data.company.service.CompanyApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CreateCompanyInteractorImpl @Inject constructor(
    private val companyFirebaseAccess: CompanyFirebaseAccessImpl,
    private val companyApiService: CompanyApiService
) : BaseInteractor<CreateCompanyInteractor.CreateCompanyListener>(), CreateCompanyInteractor {

    override fun onCreate() {
        super.onCreate()
        companyFirebaseAccess.setCompanySaveListener(CompanySaveListener())
    }

    override fun getCompanyId(name: String) {
        companyApiService.getCompanyId(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                saveCompany(it.id, it.name)
                callback?.onSaveCompanyFailure()
            }.dispose()
    }

    override fun saveCompany(id: Int, name: String) {
        companyFirebaseAccess.saveCompany(id, name)
    }

    private inner class CompanySaveListener : CompanyFirebaseAccess.CompanySaveListener {
        override fun onCompanySaveSuccess() {
            callback?.onSaveCompanySuccess()
        }

        override fun onCompanySaveFailure() {
            callback?.onSaveCompanyFailure()
        }
    }
}