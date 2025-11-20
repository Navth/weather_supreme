package org.breezyweather.sources.cwa.json

import kotlinx.serialization.Serializable

@Serializable
data class CwaAirQualityResult(
    val data: CwaAirQualityData? = null,
)
