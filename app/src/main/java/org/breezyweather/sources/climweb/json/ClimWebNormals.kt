package org.breezyweather.sources.climweb.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ClimWebNormals(
    val date: String?,
    @SerialName("max-temp") val maxTemp: Double?,
    @SerialName("maximum-temperature") val maximumTemperature: Double?,
    @SerialName("mean-maximum-temperature") val meanMaximumTemperature: Double?,
    @SerialName("temperature-maximale") val temperatureMaximale: Double?,
    @SerialName("min-temp") val minTemp: Double?,
    @SerialName("minimum-temperature") val minimumTemperature: Double?,
    @SerialName("mean-minimum-temperature") val meanMinimumTemperature: Double?,
    @SerialName("temperature-minimale") val temperatureMinimale: Double?,
)
