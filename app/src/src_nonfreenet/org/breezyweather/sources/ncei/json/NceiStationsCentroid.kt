package org.breezyweather.sources.ncei.json

import kotlinx.serialization.Serializable

@Serializable
data class NceiStationsCentroid(
    val point: List<Double>,
)
