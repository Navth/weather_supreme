package org.breezyweather.sources.cwa.json

import kotlinx.serialization.Serializable

@Serializable
data class CwaNormalsStationObsStatistics(
    val AirTemperature: CwaNormalsAirTemperature?,
)
