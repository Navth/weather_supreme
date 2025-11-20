package org.breezyweather.sources.namem.json

import kotlinx.serialization.Serializable

@Serializable
data class NamemAirQualityResult(
    val data: List<NamemStation>? = null,
)
