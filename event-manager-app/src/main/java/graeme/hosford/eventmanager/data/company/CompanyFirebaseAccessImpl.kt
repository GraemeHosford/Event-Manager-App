package graeme.hosford.eventmanager.data.company

import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

const val COMPANIES_COLLECTION = "Companies"

class CompanyFirebaseAccessImpl @Inject constructor() : CompanyFirebaseAccess {

    private var companySaveListener: CompanyFirebaseAccess.CompanySaveListener? = null

    fun setCompanySaveListener(companyListener: CompanyFirebaseAccess.CompanySaveListener) {
        this.companySaveListener = companyListener
    }

    override fun saveCompany(id: Int, name: String) {
        FirebaseFirestore.getInstance()
            .collection(COMPANIES_COLLECTION)
            .add(
                hashMapOf(
                    "id" to id,
                    "name" to name
                )
            ).addOnSuccessListener {
                companySaveListener?.onCompanySaveSuccess()
            }.addOnFailureListener {
                companySaveListener?.onCompanySaveFailure()
            }
    }
}