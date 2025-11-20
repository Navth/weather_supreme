package org.breezyweather.sources.smg.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SmgUvReport(
    @SerialName("UV-Index") val index: List<SmgValue>?,
)
