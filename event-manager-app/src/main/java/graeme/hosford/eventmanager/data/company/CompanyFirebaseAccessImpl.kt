package graeme.hosford.eventmanager.data.company

import com.google.firebase.firestore.FirebaseFirestore
import graeme.hosford.eventmanager.data.company.CompanyFirebaseAccess.Companion.COMPANIES_COLLECTION
import graeme.hosford.eventmanager.data.company.CompanyFirebaseAccess.Companion.MEMBERS_SUBCOLLECTION
import javax.inject.Inject

class CompanyFirebaseAccessImpl @Inject constructor() : CompanyFirebaseAccess {

    private var companySaveListener: CompanyFirebaseAccess.CompanySaveListener? = null
    private var addUserCallback: CompanyFirebaseAccess.AddUserListener? = null

    override fun setCompanySaveListener(comapnySaveListener: CompanyFirebaseAccess.CompanySaveListener) {
        this.companySaveListener = comapnySaveListener
    }

    override fun setAddUserListener(addUserListener: CompanyFirebaseAccess.AddUserListener) {
        this.addUserCallback = addUserListener
    }

    override fun saveCompany(id: Int, name: String) {
        FirebaseFirestore.getInstance()
            .collection(COMPANIES_COLLECTION)
            .document(id.toString())
            .set(
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

    override fun addUserToCompany(companyId: Int, userEmail: String) {
        FirebaseFirestore.getInstance()
            .collection(COMPANIES_COLLECTION)
            .document(companyId.toString())
            .collection(MEMBERS_SUBCOLLECTION)
            .add(
                hashMapOf(
                    "userEmail" to userEmail
                )
            ).addOnSuccessListener {
                addUserCallback?.onAddUserSuccess()
            }.addOnFailureListener {
                addUserCallback?.onAddUserFailure()
            }

    }
}