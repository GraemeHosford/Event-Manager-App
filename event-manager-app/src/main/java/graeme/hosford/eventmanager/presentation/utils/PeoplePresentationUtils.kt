package graeme.hosford.eventmanager.presentation.utils

import android.content.res.Resources
import graeme.hosford.eventmanager.R

class PeoplePresentationUtils private constructor() {

    companion object {
        fun getAttendeeSummary(resources: Resources, people: List<String>): String {
            if (people.isNotEmpty()) {
                return resources.getQuantityString(
                    R.plurals.attendees_summary,
                    people.size,
                    people[0],
                    people.size - 1
                )
            }

            return resources.getString(R.string.create_event_no_attendees_chosen)
        }

    }

}