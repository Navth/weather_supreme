package org.breezyweather.sources.jma.json

import kotlinx.serialization.Serializable

@Serializable
data class JmaHourlyResult(
    val areaTimeSeries: JmaHourlyAreaTimeSeries? = null,
    val pointTimeSeries: JmaHourlyPointTimeSeries? = null,
)
