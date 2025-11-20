package org.breezyweather.sources.smg.json

import kotlinx.serialization.Serializable

@Serializable
data class SmgCurrentCustom(
    val WeatherReport: List<SmgCurrentWeatherReport>?,
)
