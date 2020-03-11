package graeme.hosford.eventmanager.presentation.attendees.choose.model

object AttendeeComparator {

    val attendeeNameComparator = compareBy(AttendeesUiModel::displayName)

}