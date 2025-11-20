package org.breezyweather.sources.hko.json

import kotlinx.serialization.Serializable

@Serializable
data class HkoOneJsonWeatherForecast(
    val ForecastDate: String?,
    val ForecastWeather: String?,
    val ForecastIcon: String?,
)
