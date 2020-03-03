package graeme.hosford.eventmanager.business.company.detail

import graeme.hosford.eventmanager.business.common.BaseInteractor
import graeme.hosford.eventmanager.business.common.Interactor
import graeme.hosford.eventmanager.business.user.CurrentUserInteractor
import graeme.hosford.eventmanager.data.company.CompanyFirebaseAccess
import graeme.hosford.eventmanager.entity.company.Person
import javax.inject.Inject

class CompanyDetailInteractorImpl @Inject constructor(
    private val currentUserInteractor: CurrentUserInteractor,
    private val companyFirebaseAccess: CompanyFirebaseAccess
) : BaseInteractor<CompanyDetailInteractor.CompanyDetailCallback>(),
    CompanyDetailInteractor {

    override fun onCreate() {
        super.onCreate()
        currentUserInteractor.registerCallback(UserCallback())
        companyFirebaseAccess.setMembersListener(MemberCallback())
    }

    override fun registerManagedInteractors(): List<Interactor<*>> = listOf(currentUserInteractor)

    override fun getCompanyMembers() {
        currentUserInteractor.getUserCompanyId()
    }

    private inner class UserCallback : CurrentUserInteractor.UserCompanyListener {
        override fun onAddUserCompanySuccess() {
        }

        override fun onAddUserCompanyFailure() {
        }

        override fun onUserInfoRetrieved(info: Any?) {
            companyFirebaseAccess.getCompanyMembers(info as String)
        }

        override fun onUserInfoRetrievalFailed() {
            callback?.onDataLoadFailure()
        }
    }

    private inner class MemberCallback : CompanyFirebaseAccess.MembersListener {
        override fun onMembersRetrieved(people: List<Person>) {
            callback?.onMembersRetrieved(people)
        }

        override fun onMembersRetrievalFailed() {
            callback?.onDataLoadFailure()
        }
    }
}