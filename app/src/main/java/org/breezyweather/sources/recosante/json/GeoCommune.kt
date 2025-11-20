package org.breezyweather.sources.recosante.json

import kotlinx.serialization.Serializable

@Serializable
data class GeoCommune(
    val code: String, // INSEE
    val nom: String?,
)
