package graeme.hosford.eventmanager.business.company.create

import graeme.hosford.eventmanager.business.common.BaseInteractor
import graeme.hosford.eventmanager.data.company.CompanyFirebaseAccess
import graeme.hosford.eventmanager.data.company.CompanyFirebaseAccessImpl
import javax.inject.Inject

class CreateCompanyInteractorImpl @Inject constructor(
    private val companyFirebaseAccess: CompanyFirebaseAccessImpl
) : BaseInteractor<CreateCompanyInteractor.CreateCompanyListener>(), CreateCompanyInteractor {

    override fun onCreate() {
        super.onCreate()
        companyFirebaseAccess.setCompanySaveListener(CompanySaveListener())
    }

    override fun getCompanyId(name: String): Int {
        /* Some RxJava stuff here to handle Ruby server background stuff */
        return 0
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