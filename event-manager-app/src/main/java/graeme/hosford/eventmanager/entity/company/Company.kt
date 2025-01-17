package graeme.hosford.eventmanager.entity.company

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Company(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String
)