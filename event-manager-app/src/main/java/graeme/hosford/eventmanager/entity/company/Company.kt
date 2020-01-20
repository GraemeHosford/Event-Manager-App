package graeme.hosford.eventmanager.entity.company

import com.squareup.moshi.Json

data class Company(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "name") val name: String
)