package org.breezyweather.sources.ekuk.json

import kotlinx.serialization.Serializable

@Serializable
data class EkukStationProperties(
    val type: String?,
    val indicators: List<Double>?,
)
