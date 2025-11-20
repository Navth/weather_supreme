package org.breezyweather.sources.meteolux.json

import kotlinx.serialization.Serializable

@Serializable
data class MeteoLuxWeatherDaily(
    val date: String,
    val icon: MeteoLuxWeatherIcon?,
    val wind: MeteoLuxWeatherWind?,
    val rain: String?,
    val snow: String?,
    val temperatureMin: MeteoLuxWeatherTemperature?,
    val temperatureMax: MeteoLuxWeatherTemperature?,
    val sunshine: Double?,
    val uvIndex: Double?,
)
