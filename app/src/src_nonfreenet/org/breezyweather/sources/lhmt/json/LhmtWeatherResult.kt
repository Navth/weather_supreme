package org.breezyweather.sources.lhmt.json

import kotlinx.serialization.Serializable

@Serializable
data class LhmtWeatherResult(
    val forecastTimestamps: List<LhmtWeather>? = null,
    val observations: List<LhmtWeather>? = null,
)
