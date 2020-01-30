package graeme.hosford.eventmanager.business.company.create

import graeme.hosford.eventmanager.business.common.BaseInteractor
import graeme.hosford.eventmanager.business.common.Interactor
import graeme.hosford.eventmanager.business.user.CurrentUserInteractor
import graeme.hosford.eventmanager.data.company.CompanyFirebaseAccess
import graeme.hosford.eventmanager.data.company.CompanyFirebaseAccessImpl
import graeme.hosford.eventmanager.data.company.service.CompanyApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CreateCompanyInteractorImpl @Inject constructor(
    private val companyFirebaseAccess: CompanyFirebaseAccessImpl,
    private val companyApiService: CompanyApiService,
    private val currentUserInteractor: CurrentUserInteractor
) : BaseInteractor<CreateCompanyInteractor.CreateCompanyListener>(), CreateCompanyInteractor {

    private val disposables: CompositeDisposable = CompositeDisposable()

    override fun onCreate() {
        super.onCreate()
        companyFirebaseAccess.setCompanySaveListener(CompanySaveListener())
    }

    override fun registerManagedInteractors(): List<Interactor<*>> = listOf(currentUserInteractor)

    override fun registerCurrentUserInteractorListener(listener: CurrentUserInteractor.UserCompanyListener) {
        currentUserInteractor.registerCallback(listener)
    }

    override fun setUserCompany(companyId: String) {
        currentUserInteractor.setUserCompany(companyId)
    }

    override fun getCompanyId(name: String) {
        disposables.add(companyApiService.getCompanyId(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    callback?.onGetCompanyIdSuccess(it.id, it.name)
                },
                {
                    callback?.onGetCompanyIdFailure()
                }
            )
        )
    }

    override fun saveCompany(id: Int, name: String) {
        companyFirebaseAccess.saveCompany(id, name)
    }

    private inner class CompanySaveListener : CompanyFirebaseAccess.CompanySaveListener {
        override fun onCompanySaveSuccess(companyId: String) {
            callback?.onSaveCompanySuccess(companyId)
        }

        override fun onCompanySaveFailure() {
            callback?.onSaveCompanyFailure()
        }
    }
}