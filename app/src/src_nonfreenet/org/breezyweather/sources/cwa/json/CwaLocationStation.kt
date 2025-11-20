package org.breezyweather.sources.cwa.json

import kotlinx.serialization.Serializable

@Serializable
data class CwaLocationStation(
    val StationId: String?,
)
