package org.breezyweather.sources.ekuk.json

import kotlinx.serialization.Serializable

@Serializable
data class EkukStationsResult(
    val features: List<EkukStation>? = null,
)
