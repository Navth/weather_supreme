package org.breezyweather.sources.cwa.json

import kotlinx.serialization.Serializable

@Serializable
data class CwaAirQualityData(
    val aqi: List<CwaAirQualityAqi>?,
)
