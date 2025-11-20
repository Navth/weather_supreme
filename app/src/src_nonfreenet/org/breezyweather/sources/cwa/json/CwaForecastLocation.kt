package org.breezyweather.sources.cwa.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CwaForecastLocation(
    @SerialName("WeatherElement") val weatherElement: List<CwaForecastWeatherElement>?,
)
