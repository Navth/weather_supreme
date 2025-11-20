package org.breezyweather.sources.aemet.json

import kotlinx.serialization.Serializable

@Serializable
data class AemetDailyData(
    val periodo: String?,
    val value: Double?,
    val direccion: String?,
    val velocidad: Double?,
    val maxima: Double?,
    val minima: Double?,
)
