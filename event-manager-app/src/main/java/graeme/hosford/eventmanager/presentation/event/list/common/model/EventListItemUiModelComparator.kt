package graeme.hosford.eventmanager.presentation.event.list.common.model

object EventListItemUiModelComparator {

    val DateTimeComparator = compareBy(EventListItemUiModel::startDate)

}