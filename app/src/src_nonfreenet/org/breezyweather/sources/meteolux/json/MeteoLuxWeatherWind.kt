package org.breezyweather.sources.meteolux.json

import kotlinx.serialization.Serializable

@Serializable
data class MeteoLuxWeatherWind(
    val direction: String?,
    val speed: String?,
    val gusts: String?,
)
