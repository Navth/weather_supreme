package org.breezyweather.sources.meteolux.json

import kotlinx.serialization.Serializable

@Serializable
data class MeteoLuxWeatherHourlyTemperature(
    val temperature: List<Double>?,
    val felt: Double?,
)
