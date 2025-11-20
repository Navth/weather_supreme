package org.breezyweather.sources.cwa.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CwaCurrentGustInfo(
    @SerialName("PeakGustSpeed") val peakGustSpeed: Double?,
)
