package graeme.hosford.eventmanager.business.attendees

import graeme.hosford.eventmanager.business.common.Interactor
import graeme.hosford.eventmanager.entity.company.Person

interface AttendeesInteractor : Interactor<AttendeesInteractor.AttendeesCallback> {

    interface AttendeesCallback {
        fun onCompanyMembersRetrieved(people: List<Person>)

        fun onMemberRetrievalFailed()
    }

    fun getCompanyMembers()

}