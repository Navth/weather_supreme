package org.breezyweather.sources.smhi.json

import kotlinx.serialization.Serializable

@Serializable
data class SmhiForecastResult(
    val timeSeries: List<SmhiTimeSeries>?,
)
