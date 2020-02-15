package graeme.hosford.eventmanager.business.attendees

import graeme.hosford.eventmanager.business.common.Interactor
import graeme.hosford.eventmanager.entity.company.Member

interface AttendeesInteractor : Interactor<AttendeesInteractor.AttendeesCallback> {

    interface AttendeesCallback {
        fun onCompanyMembersRetrieved(members: List<Member>)

        fun onMemberRetrievalFailed()
    }

    fun getCompanyMembers()

}