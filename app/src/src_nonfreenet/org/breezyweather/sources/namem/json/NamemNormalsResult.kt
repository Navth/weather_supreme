package org.breezyweather.sources.namem.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NamemNormalsResult(
    @SerialName("fore_monthly") val foreMonthly: List<NamemNormals>? = null,
)
