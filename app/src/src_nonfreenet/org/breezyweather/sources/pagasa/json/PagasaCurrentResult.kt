package org.breezyweather.sources.pagasa.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PagasaCurrentResult(
    val datetime: String,
    val temperature: String?,
    val humidity: String?,
    val pressure: String?,
    @SerialName("wind_speed") val windSpeed: String?,
    @SerialName("wind_direction") val windDirection: String?,
    val latitude: String,
    val longitude: String,
)
