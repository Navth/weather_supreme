package org.breezyweather.sources.meteolux.json

import kotlinx.serialization.Serializable

@Serializable
data class MeteoLuxWeatherCity(
    val name: String?,
    val region: String?,
    val canton: String?,
    val lat: Double?,
    val long: Double?,
)
