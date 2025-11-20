package org.breezyweather.sources.cwa.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CwaForecastLocations(
    @SerialName("Location") val location: List<CwaForecastLocation>?,
)
