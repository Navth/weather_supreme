package org.breezyweather.sources.cwa.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CwaCurrentCoordinates(
    @SerialName("CoordinateName") val coordinateName: String?,
    @SerialName("StationLatitude") val stationLatitude: Double?,
)
