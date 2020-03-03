package graeme.hosford.eventmanager.data.company.member.converter

import com.google.firebase.firestore.DocumentSnapshot
import graeme.hosford.eventmanager.data.common.entity.EntityConverter
import graeme.hosford.eventmanager.entity.company.Person
import javax.inject.Inject

class MemberEntityConverter @Inject constructor() : EntityConverter<Person> {

    override fun convert(document: DocumentSnapshot): Person {
        return Person(document.getString("userEmail")!!)
    }
}