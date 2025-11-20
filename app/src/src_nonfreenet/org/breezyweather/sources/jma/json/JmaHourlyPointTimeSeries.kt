package org.breezyweather.sources.jma.json

import kotlinx.serialization.Serializable

@Serializable
data class JmaHourlyPointTimeSeries(
    val timeDefines: List<JmaHourlyTimeDefines?>?,
    val temperature: List<Double?>?,
)
