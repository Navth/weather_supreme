package org.breezyweather.sources.atmo.json

import kotlinx.serialization.Serializable

@Serializable
data class GeoResult(
    val features: List<GeoFeature>,
)
