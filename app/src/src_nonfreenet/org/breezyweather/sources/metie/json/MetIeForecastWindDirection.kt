package org.breezyweather.sources.metie.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MetIeForecastWindDirection(
    @SerialName("@deg") val deg: String?,
)
