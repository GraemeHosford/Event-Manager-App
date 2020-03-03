package graeme.hosford.eventmanager.data.company

import graeme.hosford.eventmanager.entity.company.Person

interface CompanyFirebaseAccess {

    companion object {
        const val COMPANIES_COLLECTION = "Companies"
        const val MEMBERS_SUBCOLLECTION = "Members"
    }

    interface CompanySaveListener {
        fun onCompanySaveSuccess(companyId: String)

        fun onCompanySaveFailure()
    }

    interface AddUserListener {
        fun onAddUserSuccess(companyId: String)

        fun onAddUserFailure()
    }

    interface MembersListener {
        fun onMembersRetrieved(people: List<Person>)

        fun onMembersRetrievalFailed()
    }

    fun setCompanySaveListener(comapnySaveListener: CompanySaveListener)

    fun setAddUserListener(addUserListener: AddUserListener)

    fun setMembersListener(listener: MembersListener)

    fun saveCompany(id: Int, name: String)

    fun addUserToCompany(companyId: String, userEmail: String)

    fun getCompanyMembers(companyId: String)

}