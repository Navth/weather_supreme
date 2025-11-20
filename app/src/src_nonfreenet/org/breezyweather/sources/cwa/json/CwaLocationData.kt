package org.breezyweather.sources.cwa.json

import kotlinx.serialization.Serializable

@Serializable
data class CwaLocationData(
    val aqi: List<CwaLocationAqi>?,
)
