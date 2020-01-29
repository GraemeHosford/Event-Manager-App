package graeme.hosford.eventmanager.business.company.create

import graeme.hosford.eventmanager.business.common.Interactor

interface CreateCompanyInteractor : Interactor<CreateCompanyInteractor.CreateCompanyListener> {

    interface CreateCompanyListener {

        fun onGetCompanyIdSuccess(id: Int, name: String)

        fun onGetCompanyIdFailure()

        fun onSaveCompanySuccess(companyId: String)

        fun onSaveCompanyFailure()

    }

    fun getCompanyId(name: String)

    fun saveCompany(id: Int, name: String)

}