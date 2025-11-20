package org.breezyweather.sources.openweather.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OpenWeatherForecastMain(
    val temp: Double?,
    @SerialName("feels_like") val feelsLike: Double?,
    val pressure: Int?,
    val humidity: Int?,
)
