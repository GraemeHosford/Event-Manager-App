package graeme.hosford.eventmanager.presentation.usereventdetail.detail.model

import graeme.hosford.eventmanager.entity.usereventdetail.UserEventDetail
import graeme.hosford.eventmanager.presentation.common.model.UiModelListProcessor
import javax.inject.Inject

class UserEventDetailProcessor @Inject constructor(
    private val converter: UserEventDetailUiModelConverter
): UiModelListProcessor<UserEventDetail, UserEventDetailListUiModel>(converter)