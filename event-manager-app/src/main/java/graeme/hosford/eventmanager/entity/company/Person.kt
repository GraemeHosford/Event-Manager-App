package graeme.hosford.eventmanager.entity.company

data class Person(val name: String) {

    companion object {
        const val FIRST_NAME = "firstName"
        const val LAST_NAME = "lastName"
        const val JOB_TITLE = "jobTitle"
        const val DESCRIPTION = "Description"
    }

}