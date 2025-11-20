package org.breezyweather.sources.nws.json

import kotlinx.serialization.Serializable

@Serializable
data class NwsPointResult(
    val properties: NwsPointProperties?,
)
