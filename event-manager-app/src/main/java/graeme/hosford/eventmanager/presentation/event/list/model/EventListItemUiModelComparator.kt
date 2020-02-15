package graeme.hosford.eventmanager.presentation.event.list.model

object EventListItemUiModelComparator {

    val DateTimeComparator = compareBy(EventListItemUiModel::startDate)

}