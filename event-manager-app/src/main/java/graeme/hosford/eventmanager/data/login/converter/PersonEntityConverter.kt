package graeme.hosford.eventmanager.data.login.converter

import com.google.firebase.firestore.DocumentSnapshot
import graeme.hosford.eventmanager.data.common.entity.EntityConverter
import graeme.hosford.eventmanager.entity.company.Person
import javax.inject.Inject

class PersonEntityConverter @Inject constructor() : EntityConverter<Person> {

    override fun convert(document: DocumentSnapshot): Person {
        with(document) {
            return Person(
                get(Person.FIRST_NAME) as String
            )
        }
    }
}