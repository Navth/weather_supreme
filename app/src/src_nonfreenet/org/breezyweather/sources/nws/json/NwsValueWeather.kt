package org.breezyweather.sources.nws.json

import kotlinx.serialization.Serializable

@Serializable
data class NwsValueWeather(
    val validTime: String, // Convert to Date + interval later
    val value: List<NwsValueWeatherValue>?,
)
