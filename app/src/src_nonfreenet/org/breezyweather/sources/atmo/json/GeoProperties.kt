package org.breezyweather.sources.atmo.json

import kotlinx.serialization.Serializable

@Serializable
data class GeoProperties(
    val citycode: String,
)
