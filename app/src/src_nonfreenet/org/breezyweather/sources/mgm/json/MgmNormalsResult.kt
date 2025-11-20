package org.breezyweather.sources.mgm.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MgmNormalsResult(
    @SerialName("ay") val month: Int?,
    @SerialName("gun") val day: Int?,
    @SerialName("maxOrt") val meanMax: Double?,
    @SerialName("minOrt") val meanMin: Double?,
)
