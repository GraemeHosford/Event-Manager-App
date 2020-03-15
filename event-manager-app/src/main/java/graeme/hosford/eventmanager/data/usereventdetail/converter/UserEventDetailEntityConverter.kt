package graeme.hosford.eventmanager.data.usereventdetail.converter

import com.google.firebase.firestore.DocumentSnapshot
import graeme.hosford.eventmanager.data.common.entity.EntityConverter
import graeme.hosford.eventmanager.data.usereventdetail.DETAILS_KEY
import graeme.hosford.eventmanager.data.usereventdetail.SUBJECT_KEY
import graeme.hosford.eventmanager.entity.usereventdetail.UserEventDetail
import javax.inject.Inject

class UserEventDetailEntityConverter @Inject constructor(): EntityConverter<UserEventDetail> {

    override fun convert(document: DocumentSnapshot): UserEventDetail {
        return UserEventDetail(
            "Placeholder name",
            document.getString(SUBJECT_KEY)!!,
            document.getString(DETAILS_KEY)!!
        )
    }
}