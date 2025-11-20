package org.breezyweather.sources.openweather.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OpenWeatherForecastPrecipitation(
    @SerialName("3h") val cumul3h: Double?,
)
