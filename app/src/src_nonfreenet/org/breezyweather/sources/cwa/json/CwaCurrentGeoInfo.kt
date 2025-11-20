package org.breezyweather.sources.cwa.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CwaCurrentGeoInfo(
    @SerialName("Coordinates") val coordinates: List<CwaCurrentCoordinates>?,
    @SerialName("StationAltitude") val stationAltitude: String?,
)
