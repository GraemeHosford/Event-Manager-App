package graeme.hosford.eventmanager.business.attendees.choose

import graeme.hosford.eventmanager.business.common.Interactor
import graeme.hosford.eventmanager.entity.company.Person

interface ChooseAttendeesInteractor :
    Interactor<ChooseAttendeesInteractor.ChooseAttendeesCallback> {

    interface ChooseAttendeesCallback {
        fun onCompanyMembersRetrieved(people: List<Person>)

        fun onMemberRetrievalFailed()
    }

    fun getCompanyMembers()

}