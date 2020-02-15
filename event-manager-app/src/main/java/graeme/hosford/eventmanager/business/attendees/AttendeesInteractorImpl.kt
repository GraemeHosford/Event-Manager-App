package graeme.hosford.eventmanager.business.attendees

import graeme.hosford.eventmanager.business.common.BaseInteractor
import graeme.hosford.eventmanager.business.common.Interactor
import graeme.hosford.eventmanager.business.user.CurrentUserInteractor
import graeme.hosford.eventmanager.data.company.CompanyFirebaseAccess
import graeme.hosford.eventmanager.entity.company.Member
import javax.inject.Inject

class AttendeesInteractorImpl @Inject constructor(
    private val companyFirebaseAccess: CompanyFirebaseAccess,
    private val currentUserInteractor: CurrentUserInteractor
) : BaseInteractor<AttendeesInteractor.AttendeesCallback>(),
    AttendeesInteractor {

    override fun onCreate() {
        super.onCreate()
        companyFirebaseAccess.setMembersListener(MembersLisener())
        currentUserInteractor.registerCallback(CurrentUserCallback())
    }

    override fun registerManagedInteractors(): List<Interactor<*>> = listOf(currentUserInteractor)

    override fun getCompanyMembers() {
        currentUserInteractor.getUserCompanyId()
    }

    private inner class CurrentUserCallback : CurrentUserInteractor.UserCompanyListener {
        override fun onAddUserCompanySuccess() {
        }

        override fun onAddUserCompanyFailure() {
        }

        override fun onUserInfoRetrieved(info: Any?) {
            companyFirebaseAccess.getCompanyMembers(info as String)
        }

        override fun onUserInfoRetrievalFailed() {
            callback?.onMemberRetrievalFailed()
        }
    }

    private inner class MembersLisener : CompanyFirebaseAccess.MembersListener {
        override fun onMembersRetrieved(members: List<Member>) {
            callback?.onCompanyMembersRetrieved(members)
        }

        override fun onMembersRetrievalFailed() {
            callback?.onMemberRetrievalFailed()
        }
    }
}