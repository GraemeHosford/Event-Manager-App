package graeme.hosford.eventmanager.entity.company

data class Person(
    val id: String,
    val firstName: String,
    val lastName: String,
    val companyId: String?,
    val jobTitle: String,
    val description: String
) {

    companion object {
        const val ID = "userEmail"
        const val FIRST_NAME = "firstName"
        const val LAST_NAME = "lastName"
        const val COMPANY_ID = "companyId"
        const val JOB_TITLE = "jobTitle"
        const val DESCRIPTION = "Description"
    }

}