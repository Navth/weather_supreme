package org.breezyweather.sources.smg.json

import kotlinx.serialization.Serializable

@Serializable
data class SmgCurrentWeatherReport(
    val station: List<SmgCurrentStation>?,
)
