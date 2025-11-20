package org.breezyweather.sources.atmo.json

import kotlinx.serialization.Serializable

@Serializable
data class AtmoPointResult(
    val polluants: List<AtmoPointPolluant>? = null,
)
