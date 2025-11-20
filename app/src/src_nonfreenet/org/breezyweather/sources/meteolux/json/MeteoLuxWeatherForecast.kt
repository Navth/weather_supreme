package org.breezyweather.sources.meteolux.json

import kotlinx.serialization.Serializable

@Serializable
data class MeteoLuxWeatherForecast(
    val current: MeteoLuxWeatherCurrent?,
    val hourly: List<MeteoLuxWeatherHourly>?,
    val daily: List<MeteoLuxWeatherDaily>?,
)
