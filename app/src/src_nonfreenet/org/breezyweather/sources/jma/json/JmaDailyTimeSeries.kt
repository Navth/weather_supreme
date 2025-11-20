package org.breezyweather.sources.jma.json

import kotlinx.serialization.Serializable
import org.breezyweather.common.serializer.DateSerializer
import java.util.Date

@Serializable
data class JmaDailyTimeSeries(
    @Suppress("ktlint") val timeDefines: List<@Serializable(DateSerializer::class) Date>?,
    val areas: List<JmaDailyArea>?,
)
