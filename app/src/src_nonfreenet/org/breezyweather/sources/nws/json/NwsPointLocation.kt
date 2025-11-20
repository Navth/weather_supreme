package org.breezyweather.sources.nws.json

import kotlinx.serialization.Serializable

@Serializable
data class NwsPointLocation(
    val properties: NwsPointLocationProperties?,
    val geometry: NwsPointLocationGeometry?,
)
