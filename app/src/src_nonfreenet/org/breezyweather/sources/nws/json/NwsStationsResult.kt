package org.breezyweather.sources.nws.json

import kotlinx.serialization.Serializable

@Serializable
data class NwsStationsResult(
    val features: List<NwsPointLocation>? = null,
)
