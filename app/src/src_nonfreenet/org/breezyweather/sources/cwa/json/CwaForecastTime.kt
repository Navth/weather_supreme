package org.breezyweather.sources.cwa.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.breezyweather.common.serializer.DateSerializer
import java.util.Date

@Serializable
data class CwaForecastTime(
    @SerialName("DataTime") @Serializable(DateSerializer::class) val dataTime: Date?,
    @SerialName("StartTime") @Serializable(DateSerializer::class) val startTime: Date?,
    @SerialName("ElementValue") val elementValue: List<CwaForecastElementValue>?,
)
