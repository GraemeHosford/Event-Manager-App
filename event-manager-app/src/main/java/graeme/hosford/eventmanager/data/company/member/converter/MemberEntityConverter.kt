package graeme.hosford.eventmanager.data.company.member.converter

import com.google.firebase.firestore.DocumentSnapshot
import graeme.hosford.eventmanager.data.common.entity.EntityConverter
import graeme.hosford.eventmanager.entity.company.Member
import javax.inject.Inject

class MemberEntityConverter @Inject constructor() : EntityConverter<Member> {

    override fun convert(document: DocumentSnapshot): Member {
        return Member(document.getString("name")!!)
    }
}