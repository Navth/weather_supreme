package org.breezyweather.sources.meteolux.json

import kotlinx.serialization.Serializable

@Serializable
data class MeteoLuxWeatherTemperature(
    val temperature: Double?,
    val felt: Double?,
)
