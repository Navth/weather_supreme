package org.breezyweather.sources.metie.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MetIeForecastPrecipitation(
    @SerialName("@value") val value: String?,
    @SerialName("@probability") val probability: String?,
)
