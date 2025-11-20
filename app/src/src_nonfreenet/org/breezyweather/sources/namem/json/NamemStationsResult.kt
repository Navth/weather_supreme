package org.breezyweather.sources.namem.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NamemStationsResult(
    @SerialName("aimag_sum") val locations: List<NamemStation>? = null,
)
