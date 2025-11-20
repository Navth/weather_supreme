package org.breezyweather.sources.meteolux.json

import kotlinx.serialization.Serializable

@Serializable
data class MeteoLuxWeatherIcon(
    val id: Int?,
    val name: String?,
)
