package org.breezyweather.sources.hko.json

import kotlinx.serialization.Serializable

@Serializable
data class HkoDailyForecast(
    val ForecastDate: String,
    val ForecastChanceOfRain: String?,
    val ForecastDailyWeather: Int?,
)
