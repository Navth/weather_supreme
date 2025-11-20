package org.breezyweather.sources.meteoam.json

import kotlinx.serialization.Serializable

@Serializable
data class MeteoAmForecastExtraInfo(
    val timezone: String?,
    val stats: List<MeteoAmForecastStats>?,
)
