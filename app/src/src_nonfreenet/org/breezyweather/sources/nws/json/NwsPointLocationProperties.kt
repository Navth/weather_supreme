package org.breezyweather.sources.nws.json

import kotlinx.serialization.Serializable

@Serializable
data class NwsPointLocationProperties(
    val city: String?,
    val state: String?,
    val stationIdentifier: String?,
)
