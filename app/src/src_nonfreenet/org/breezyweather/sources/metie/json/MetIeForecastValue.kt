package org.breezyweather.sources.metie.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MetIeForecastValue(
    @SerialName("@value") val value: String?,
)
