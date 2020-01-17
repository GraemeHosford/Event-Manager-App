package graeme.hosford.eventmanager.business.company.create

import graeme.hosford.eventmanager.business.common.BaseInteractor
import graeme.hosford.eventmanager.data.company.CompanyFirebaseAccess
import graeme.hosford.eventmanager.data.company.CompanyFirebaseAccessImpl
import javax.inject.Inject

class CreateCompanyInteractorImpl @Inject constructor(
    private val companyFirebaseAccess: CompanyFirebaseAccessImpl
) : BaseInteractor(), CreateCompanyInteractor {

    private var listener: CreateCompanyInteractor.CreateCompanyListener? = null

    override fun onCreate() {
        super.onCreate()
        companyFirebaseAccess.setCompanySaveListener(CompanySaveListener())
    }

    fun setCreateCompanyListener(createCompanyListener: CreateCompanyInteractor.CreateCompanyListener) {
        this.listener = createCompanyListener
    }

    override fun getCompanyId(): Int {
        /* Some RxJava stuff here to handle Ruby server background stuff */
        return 0
    }

    override fun saveCompany(id: Int, name: String) {
        companyFirebaseAccess.saveCompany(id, name)
    }

    private inner class CompanySaveListener : CompanyFirebaseAccess.CompanySaveListener {
        override fun onCompanySaveSuccess() {
            listener?.onSaveCompanySuccess()
        }

        override fun onCompanySaveFailure() {
            listener?.onSaveCompanyFailure()
        }
    }
}