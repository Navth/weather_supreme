package org.breezyweather.sources.smg.json

import kotlinx.serialization.Serializable

@Serializable
data class SmgForecastCustom(
    val WeatherForecast: List<SmgForecastWeatherForecast>?,
)
