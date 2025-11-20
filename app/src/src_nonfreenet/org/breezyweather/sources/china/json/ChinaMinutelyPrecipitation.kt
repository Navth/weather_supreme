package org.breezyweather.sources.china.json

import kotlinx.serialization.Serializable
import org.breezyweather.common.serializer.DateSerializer
import java.util.Date

@Serializable
data class ChinaMinutelyPrecipitation(
    @Serializable(DateSerializer::class) val pubTime: Date?,
    val weather: String?,
    val description: String?,
    val value: List<Double>?,
)
