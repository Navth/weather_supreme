package org.breezyweather.sources.openweather.json

import kotlinx.serialization.Serializable

@Serializable
data class OpenWeatherForecast(
    val dt: Long? = null,
    val main: OpenWeatherForecastMain? = null,
    val weather: List<OpenWeatherForecastWeather>? = null,
    val clouds: OpenWeatherForecastClouds? = null,
    val wind: OpenWeatherForecastWind? = null,
    val visibility: Int? = null,
    val pop: Double? = null,
    val rain: OpenWeatherForecastPrecipitation? = null,
    val snow: OpenWeatherForecastPrecipitation? = null,
)
