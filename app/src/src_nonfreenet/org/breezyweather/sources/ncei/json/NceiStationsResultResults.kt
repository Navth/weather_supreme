package org.breezyweather.sources.ncei.json

import kotlinx.serialization.Serializable

@Serializable
data class NceiStationsResultResults(
    val stations: List<NceiStationsResultResultsStation>,
    val centroid: NceiStationsCentroid,
)
