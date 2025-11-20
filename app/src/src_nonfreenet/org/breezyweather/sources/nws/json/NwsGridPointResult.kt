package org.breezyweather.sources.nws.json

import kotlinx.serialization.Serializable

@Serializable
data class NwsGridPointResult(
    val properties: NwsGridPointProperties? = null,
)
