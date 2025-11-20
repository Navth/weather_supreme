package org.breezyweather.sources.metie.json

import kotlinx.serialization.Serializable

@Serializable
data class MetIeForecastWindSpeed(
    val kph: Int?,
)
