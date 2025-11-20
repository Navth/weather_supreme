package org.breezyweather.sources.smg.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SmgUvResult(
    @SerialName("ActualUV-Index") val UV: SmgUvRoot? = null,
)
