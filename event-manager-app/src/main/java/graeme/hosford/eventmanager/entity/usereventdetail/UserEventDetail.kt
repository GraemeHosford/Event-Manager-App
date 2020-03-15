package graeme.hosford.eventmanager.entity.usereventdetail

data class UserEventDetail(val eventName: String, val subject: String, val detail: String) {
    companion object {
        const val EVENT_NAME_KEY = "EventName"
        const val SUBJECT_KEY = "Subject"
        const val DETAIL_KEY = "Detail"
    }
}