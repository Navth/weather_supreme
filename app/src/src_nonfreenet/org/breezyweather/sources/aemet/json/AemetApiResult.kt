package org.breezyweather.sources.aemet.json

import kotlinx.serialization.Serializable

@Serializable
data class AemetApiResult(
    val datos: String? = null,
)
