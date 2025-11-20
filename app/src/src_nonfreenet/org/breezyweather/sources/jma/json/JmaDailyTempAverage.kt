package org.breezyweather.sources.jma.json

import kotlinx.serialization.Serializable

@Serializable
data class JmaDailyTempAverage(
    val areas: List<JmaDailyArea>?,
)
