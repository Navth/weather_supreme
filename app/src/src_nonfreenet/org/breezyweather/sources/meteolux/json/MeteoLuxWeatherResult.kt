package org.breezyweather.sources.meteolux.json

import kotlinx.serialization.Serializable

@Serializable
data class MeteoLuxWeatherResult(
    val city: MeteoLuxWeatherCity? = null,
    val forecast: MeteoLuxWeatherForecast? = null,
    val vigilances: List<MeteoLuxWeatherVigilance>? = null,
)
