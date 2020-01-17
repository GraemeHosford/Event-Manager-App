package graeme.hosford.eventmanager.data.company

interface CompanyFirebaseAccess {

    interface CompanySaveListener {
        fun onCompanySaveSuccess()

        fun onCompanySaveFailure()
    }

    fun saveCompany(id: Int, name: String)

}