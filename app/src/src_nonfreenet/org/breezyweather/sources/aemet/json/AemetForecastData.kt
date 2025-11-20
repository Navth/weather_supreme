package org.breezyweather.sources.aemet.json

import kotlinx.serialization.Serializable

@Serializable
data class AemetForecastData(
    val periodo: String?,
    val value: String?,
    val direccion: List<String>?,
    val velocidad: List<String>?,
)
