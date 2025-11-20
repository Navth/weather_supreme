package org.breezyweather.sources.china.json

import kotlinx.serialization.Serializable
import org.breezyweather.common.serializer.DateSerializer
import java.util.Date

@Serializable
data class ChinaValueListInt(
    @Serializable(DateSerializer::class) val pubTime: Date?,
    val value: List<Int>?,
)
