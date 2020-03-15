package graeme.hosford.eventmanager.presentation.usereventdetail.detail.model

object UserEventDetailListComparator {

    val userEventDetailComparator = compareBy(UserEventDetailListUiModel::eventName)

}