package org.breezyweather.sources.smhi.json

import kotlinx.serialization.Serializable
import org.breezyweather.common.serializer.DateSerializer
import java.util.Date

@Serializable
data class SmhiTimeSeries(
    @Serializable(DateSerializer::class) val validTime: Date,
    val parameters: List<SmhiParameter>,
)
