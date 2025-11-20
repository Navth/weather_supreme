package org.breezyweather.sources.nws.json

import kotlinx.serialization.Serializable

@Serializable
data class NwsPointLocationGeometry(
    val coordinates: List<Double>?,
)
