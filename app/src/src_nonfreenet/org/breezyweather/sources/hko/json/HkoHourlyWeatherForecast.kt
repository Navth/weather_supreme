package org.breezyweather.sources.hko.json

import kotlinx.serialization.Serializable

@Serializable
data class HkoHourlyWeatherForecast(
    val ForecastHour: String,
    val ForecastRelativeHumidity: Double?,
    val ForecastTemperature: Double?,
    val ForecastWeather: Int?,
    val ForecastWindDirection: Double?,
    val ForecastWindSpeed: Double?,
)
