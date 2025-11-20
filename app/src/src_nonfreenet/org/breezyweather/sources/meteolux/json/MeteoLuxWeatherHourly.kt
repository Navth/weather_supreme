package org.breezyweather.sources.meteolux.json

import kotlinx.serialization.Serializable

@Serializable
data class MeteoLuxWeatherHourly(
    val date: String,
    val icon: MeteoLuxWeatherIcon?,
    val wind: MeteoLuxWeatherWind?,
    val rain: String?,
    val snow: String?,
    val temperature: MeteoLuxWeatherHourlyTemperature?,
)
