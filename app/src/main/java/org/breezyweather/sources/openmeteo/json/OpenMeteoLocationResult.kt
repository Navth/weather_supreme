package org.breezyweather.sources.openmeteo.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Open Meteo geocoding
 */
@Serializable
data class OpenMeteoLocationResult(
    val id: Int,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val timezone: String?,
    @SerialName("country_code") val countryCode: String?,
    val country: String?,
    val admin1: String?,
    val admin2: String?,
    val admin3: String?,
    val admin4: String?,
)
