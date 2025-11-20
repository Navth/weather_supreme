package org.breezyweather.sources.jma.json

import kotlinx.serialization.Serializable
import org.breezyweather.common.serializer.DateSerializer
import java.util.Date

@Serializable
data class JmaAlertResult(
    @Serializable(DateSerializer::class) val reportDatetime: Date? = null,
    val publishingOffice: String? = null,
    val headlineText: String? = null,
    val areaTypes: List<JmaAlertAreaTypes>? = null,
)
