package org.breezyweather.sources.atmo.json

import kotlinx.serialization.Serializable

@Serializable
data class AtmoFrancePollenResult(
    val features: List<AtmoFrancePollenFeature>? = null,
)
