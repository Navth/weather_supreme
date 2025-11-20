package org.breezyweather.sources.brightsky.json

import kotlinx.serialization.Serializable

@Serializable
data class BrightSkyWeatherResult(
    val weather: List<BrightSkyWeather>? = null,
)
