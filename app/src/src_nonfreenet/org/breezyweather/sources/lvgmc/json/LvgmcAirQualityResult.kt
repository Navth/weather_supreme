package org.breezyweather.sources.lvgmc.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LvgmcAirQualityResult(
    @SerialName("datums") val time: String?,
    @SerialName("vertiba") val value: Double?,
    @SerialName("kods") val code: String?,
)
