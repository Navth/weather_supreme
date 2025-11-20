package org.breezyweather.sources.openmeteo.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Open Meteo geocoding
 */
@Serializable
data class OpenMeteoLocationResults(
    val results: List<OpenMeteoLocationResult>?,
    @SerialName("generationtime_ms") val generationtimeMs: Double?,
)
