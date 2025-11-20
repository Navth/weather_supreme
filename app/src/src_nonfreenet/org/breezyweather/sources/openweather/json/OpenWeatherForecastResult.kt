package org.breezyweather.sources.openweather.json

import kotlinx.serialization.Serializable

/**
 * OpenWeather One Call result.
 */
@Serializable
data class OpenWeatherForecastResult(
    val list: List<OpenWeatherForecast>? = null,
)
