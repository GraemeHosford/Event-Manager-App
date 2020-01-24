package graeme.hosford.eventmanager.data.company

interface CompanyFirebaseAccess {

    companion object {
        const val COMPANIES_COLLECTION = "Companies"
        const val MEMBERS_SUBCOLLECTION = "Members"
    }

    interface CompanySaveListener {
        fun onCompanySaveSuccess()

        fun onCompanySaveFailure()
    }

    interface AddUserListener {
        fun onAddUserSuccess()

        fun onAddUserFailure()
    }

    fun setCompanySaveListener(comapnySaveListener: CompanySaveListener)

    fun setAddUserListener(addUserListener: AddUserListener)

    fun saveCompany(id: Int, name: String)

    fun addUserToCompany(companyId: Int, userEmail: String)

}