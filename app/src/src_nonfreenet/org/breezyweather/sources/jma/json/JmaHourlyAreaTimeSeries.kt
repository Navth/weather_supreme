package org.breezyweather.sources.jma.json

import kotlinx.serialization.Serializable

@Serializable
data class JmaHourlyAreaTimeSeries(
    val timeDefines: List<JmaHourlyTimeDefines?>?,
    val weather: List<String?>?,
    val wind: List<JmaHourlyWind?>?,
)
