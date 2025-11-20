package org.breezyweather.sources.openweather.json

import kotlinx.serialization.Serializable

@Serializable
data class OpenWeatherForecastWeather(
    val id: Int?,
    val main: String?,
    val description: String?,
    val icon: String?,
)
