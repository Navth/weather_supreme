package org.breezyweather.sources.hko.json

import kotlinx.serialization.Serializable

@Serializable
data class HkoOneJsonF9d(
    val WeatherForecast: List<HkoOneJsonWeatherForecast>?,
)
