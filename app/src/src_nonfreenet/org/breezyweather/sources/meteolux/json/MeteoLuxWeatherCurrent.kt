package org.breezyweather.sources.meteolux.json

import kotlinx.serialization.Serializable

@Serializable
data class MeteoLuxWeatherCurrent(
    val icon: MeteoLuxWeatherIcon?,
    val wind: MeteoLuxWeatherWind?,
    val temperature: MeteoLuxWeatherTemperature?,
)
