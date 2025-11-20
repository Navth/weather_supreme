package org.breezyweather.sources.meteolux.json

import kotlinx.serialization.Serializable
import org.breezyweather.common.serializer.DateSerializer
import java.util.Date

@Serializable
data class MeteoLuxWeatherVigilance(
    @Serializable(DateSerializer::class) val datetimeStart: Date?,
    @Serializable(DateSerializer::class) val datetimeEnd: Date?,
    val level: Int?,
    val type: Int?,
    val group: Int?,
    val region: String?,
    val description: String?,
)
