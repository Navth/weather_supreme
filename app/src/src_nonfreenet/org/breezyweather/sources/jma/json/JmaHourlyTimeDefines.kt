package org.breezyweather.sources.jma.json

import kotlinx.serialization.Serializable
import org.breezyweather.common.serializer.DateSerializer
import java.util.Date

@Serializable
data class JmaHourlyTimeDefines(
    @Serializable(DateSerializer::class) val dateTime: Date?,
)
