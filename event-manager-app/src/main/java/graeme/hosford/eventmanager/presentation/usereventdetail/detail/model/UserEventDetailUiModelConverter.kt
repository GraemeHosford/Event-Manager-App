package graeme.hosford.eventmanager.presentation.usereventdetail.detail.model

import graeme.hosford.eventmanager.entity.usereventdetail.UserEventDetail
import graeme.hosford.eventmanager.presentation.common.model.UiModelConverter
import javax.inject.Inject

class UserEventDetailUiModelConverter @Inject constructor() :
    UiModelConverter<UserEventDetail, UserEventDetailListUiModel> {

    override fun toUiModel(entity: UserEventDetail): UserEventDetailListUiModel {
        return UserEventDetailListUiModel(
            entity.eventName,
            entity.subject,
            entity.detail
        )
    }
}