package graeme.hosford.eventmanager.presentation.attendees.model

object AttendeeComparator {

    val attendeeNameComparator = compareBy(AttendeesUiModel::name)

}