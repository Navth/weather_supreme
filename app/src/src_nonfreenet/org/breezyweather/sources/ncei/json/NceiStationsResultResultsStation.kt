package org.breezyweather.sources.ncei.json

import kotlinx.serialization.Serializable

@Serializable
data class NceiStationsResultResultsStation(
    val id: String,
    val name: String,
)
