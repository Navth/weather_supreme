package org.breezyweather.sources.pagasa.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PagasaLocationResult(
    @SerialName("site_id") val siteId: String,
    val latitude: String,
    val longitude: String,
)
