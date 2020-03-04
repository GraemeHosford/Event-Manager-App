package graeme.hosford.eventmanager.data.company

import com.google.firebase.firestore.FirebaseFirestore
import graeme.hosford.eventmanager.data.company.CompanyFirebaseAccess.Companion.COMPANIES_COLLECTION
import graeme.hosford.eventmanager.data.company.CompanyFirebaseAccess.Companion.MEMBERS_SUBCOLLECTION
import graeme.hosford.eventmanager.data.login.USERS_COLLECTION
import graeme.hosford.eventmanager.data.login.converter.PersonEntityConverter
import graeme.hosford.eventmanager.entity.company.Person
import javax.inject.Inject

class CompanyFirebaseAccessImpl @Inject constructor(
    private val memberConverter: PersonEntityConverter
) : CompanyFirebaseAccess {

    private lateinit var companySaveListener: CompanyFirebaseAccess.CompanySaveListener
    private lateinit var addUserCallback: CompanyFirebaseAccess.AddUserListener
    private lateinit var memberslistener: CompanyFirebaseAccess.MembersListener

    override fun setCompanySaveListener(comapnySaveListener: CompanyFirebaseAccess.CompanySaveListener) {
        this.companySaveListener = comapnySaveListener
    }

    override fun setAddUserListener(addUserListener: CompanyFirebaseAccess.AddUserListener) {
        this.addUserCallback = addUserListener
    }

    override fun setMembersListener(listener: CompanyFirebaseAccess.MembersListener) {
        this.memberslistener = listener
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
                companySaveListener.onCompanySaveSuccess(id.toString())
            }.addOnFailureListener {
                companySaveListener.onCompanySaveFailure()
            }
    }

    override fun addUserToCompany(companyId: String, userEmail: String) {
        FirebaseFirestore.getInstance()
            .collection(COMPANIES_COLLECTION)
            .document(companyId)
            .collection(MEMBERS_SUBCOLLECTION)
            .add(
                hashMapOf(
                    "userEmail" to userEmail
                )
            ).addOnSuccessListener {
                addUserCallback.onAddUserSuccess(companyId)
            }.addOnFailureListener {
                addUserCallback.onAddUserFailure()
            }
    }

    override fun getCompanyMembers(companyId: String) {
        FirebaseFirestore.getInstance()
            .collection(USERS_COLLECTION)
            .whereEqualTo(Person.COMPANY_ID, companyId)
            .get()
            .addOnSuccessListener { query ->
                val entities = ArrayList<Person>()
                query.documents.forEach {
                    entities.add(memberConverter.convert(it))
                }
                memberslistener.onMembersRetrieved(entities)
            }.addOnFailureListener {
                memberslistener.onMembersRetrievalFailed()
            }
    }
}