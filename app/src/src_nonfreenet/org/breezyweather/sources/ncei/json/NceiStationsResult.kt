package org.breezyweather.sources.ncei.json

import kotlinx.serialization.Serializable

@Serializable
data class NceiStationsResult(
    val results: List<NceiStationsResultResults>? = null,
)
