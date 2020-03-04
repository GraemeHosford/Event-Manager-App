package graeme.hosford.eventmanager.data.login.converter

import com.google.firebase.firestore.DocumentSnapshot
import graeme.hosford.eventmanager.data.common.entity.EntityConverter
import graeme.hosford.eventmanager.entity.company.Person
import javax.inject.Inject

class PersonEntityConverter @Inject constructor() : EntityConverter<Person> {

    override fun convert(document: DocumentSnapshot): Person {
        with(document) {
            return Person(
                get(Person.ID) as String,
                get(Person.FIRST_NAME) as String? ?: "",
                get(Person.LAST_NAME) as String? ?: "",
                get(Person.COMPANY_ID) as String,
                get(Person.JOB_TITLE) as String? ?: "",
                get(Person.DESCRIPTION) as String? ?: ""
            )
        }
    }
}