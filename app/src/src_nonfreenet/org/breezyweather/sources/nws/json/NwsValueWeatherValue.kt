package org.breezyweather.sources.nws.json

import kotlinx.serialization.Serializable

@Serializable
data class NwsValueWeatherValue(
    val coverage: String?,
    val weather: String?,
    val intensity: String?,
    val attributes: List<String>?,
)
