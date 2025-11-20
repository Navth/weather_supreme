package org.breezyweather.sources.mf.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MfNormalsStats(
    val period: String?,
    @SerialName("T_min") val tMin: Double?,
    @SerialName("T_max") val tMax: Double?,
)
