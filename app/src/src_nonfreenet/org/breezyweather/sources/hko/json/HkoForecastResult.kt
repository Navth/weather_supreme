package org.breezyweather.sources.hko.json

import kotlinx.serialization.Serializable

@Serializable
data class HkoForecastResult(
    val DailyForecast: List<HkoDailyForecast>? = null,
    val HourlyWeatherForecast: List<HkoHourlyWeatherForecast>? = null,
)
