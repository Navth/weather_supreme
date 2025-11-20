package org.breezyweather.sources.namem.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.breezyweather.common.serializer.DateSerializer
import java.util.Date

@Serializable
data class NamemHourlyForecast(
    @Serializable(DateSerializer::class) val fdate: Date?,
    val tem: String?,
    val pre: String?,
    val wnd: String?,
    @SerialName("pre_prob") val preProb: String?,
)
