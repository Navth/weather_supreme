package org.breezyweather.sources.mf.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MfHistoryTemperature(
    val value: Double?,
    @SerialName("windchill") val windChill: Double?,
)
