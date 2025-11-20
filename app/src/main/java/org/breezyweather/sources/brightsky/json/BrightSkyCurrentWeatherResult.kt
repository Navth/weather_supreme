package org.breezyweather.sources.brightsky.json

import kotlinx.serialization.Serializable

@Serializable
data class BrightSkyCurrentWeatherResult(
    val weather: BrightSkyCurrentWeather? = null,
)
