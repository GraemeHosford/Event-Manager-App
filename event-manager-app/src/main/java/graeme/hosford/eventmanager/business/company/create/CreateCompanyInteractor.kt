package graeme.hosford.eventmanager.business.company.create

interface CreateCompanyInteractor {

    interface CreateCompanyListener {

        fun onGetCompanyIdSuccess(id: Int, name: String)

        fun onGetCompanyIdFailure()

        fun onSaveCompanySuccess()

        fun onSaveCompanyFailure()

    }

    fun getCompanyId(name: String): Int

    fun saveCompany(id: Int, name: String)

}