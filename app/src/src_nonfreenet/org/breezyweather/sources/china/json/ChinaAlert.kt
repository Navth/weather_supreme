package org.breezyweather.sources.china.json

import kotlinx.serialization.Serializable
import org.breezyweather.common.serializer.DateSerializer
import java.util.Date

@Serializable
data class ChinaAlert(
    val locationKey: String?,
    val level: String?,
    @Serializable(DateSerializer::class) val pubTime: Date?,
    val alertId: String?,
    val detail: String?,
    val title: String?,
    val type: String?,
)
