package org.breezyweather.sources.mf.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MfCurrentGridded(
    @SerialName("T") val temperature: Double?,
    @SerialName("wind_speed") val windSpeed: Double?,
    @SerialName("wind_direction") val windDirection: Int?,
    @SerialName("wind_icon") val windIcon: String?,
    @SerialName("weather_icon") val weatherIcon: String?,
    @SerialName("weather_description") val weatherDescription: String?,
)
