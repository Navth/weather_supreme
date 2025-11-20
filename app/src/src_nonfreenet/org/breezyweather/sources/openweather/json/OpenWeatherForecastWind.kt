package org.breezyweather.sources.openweather.json

import kotlinx.serialization.Serializable

@Serializable
data class OpenWeatherForecastWind(
    val speed: Double?,
    val deg: Int?,
    val gust: Double?,
)
