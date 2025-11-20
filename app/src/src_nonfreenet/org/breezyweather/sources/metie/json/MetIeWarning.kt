package org.breezyweather.sources.metie.json

import kotlinx.serialization.Serializable
import org.breezyweather.common.serializer.DateSerializer
import java.util.Date

@Serializable
data class MetIeWarning(
    val id: String,
    val severity: String?,
    val level: String?,
    @Serializable(DateSerializer::class) val onset: Date?,
    @Serializable(DateSerializer::class) val expiry: Date?,
    val headline: String?,
    val description: String?,
    val regions: List<String>,
)
