package org.breezyweather.sources.lvgmc.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LvgmcAirQualityLocationResult(
    val id: Long?,
    @SerialName("grupa") val group: String?,
    @SerialName("lat") val latitude: Double?,
    @SerialName("lon") val longitude: Double?,
    @SerialName("ir_aktivs") val isActive: Boolean?,
)
