package org.breezyweather.sources.jma.json

import kotlinx.serialization.Serializable

@Serializable
data class JmaDailyResult(
    val timeSeries: List<JmaDailyTimeSeries>? = null,
    val tempAverage: JmaDailyTempAverage? = null,
)
