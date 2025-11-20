package org.breezyweather.sources.aemet.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AemetNormalsResult(
    val mes: String? = null,
    @SerialName("tm_max_md") val max: String? = null,
    @SerialName("tm_min_md") val min: String? = null,
)
