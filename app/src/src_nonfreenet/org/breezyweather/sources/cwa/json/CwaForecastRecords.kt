package org.breezyweather.sources.cwa.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CwaForecastRecords(
    @SerialName("Locations") val locations: List<CwaForecastLocations>?,
)
