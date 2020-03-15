package graeme.hosford.eventmanager.data.usereventdetail.converter

import com.google.firebase.firestore.DocumentSnapshot
import graeme.hosford.eventmanager.data.common.entity.EntityConverter
import graeme.hosford.eventmanager.entity.usereventdetail.UserEventDetail
import javax.inject.Inject

class UserEventDetailEntityConverter @Inject constructor(): EntityConverter<UserEventDetail> {

    override fun convert(document: DocumentSnapshot): UserEventDetail {
        return UserEventDetail(
            document.getString(UserEventDetail.EVENT_NAME_KEY)!!,
            document.getString(UserEventDetail.SUBJECT_KEY)!!,
            document.getString(UserEventDetail.DETAIL_KEY)!!
        )
    }
}