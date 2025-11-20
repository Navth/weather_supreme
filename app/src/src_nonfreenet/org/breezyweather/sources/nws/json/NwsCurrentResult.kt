package org.breezyweather.sources.nws.json

import kotlinx.serialization.Serializable

@Serializable
data class NwsCurrentResult(
    val geometry: NwsPointLocationGeometry? = null,
    val properties: NwsCurrentProperties? = null,
)
