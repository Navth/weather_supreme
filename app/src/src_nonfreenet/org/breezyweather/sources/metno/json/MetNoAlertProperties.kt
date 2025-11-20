package org.breezyweather.sources.metno.json

import kotlinx.serialization.Serializable
import org.breezyweather.common.serializer.DateSerializer
import java.util.Date

/**
 * MET Norway alert properties
 */
@Serializable
data class MetNoAlertProperties(
    val id: String,
    val eventAwarenessName: String?,
    val description: String?,
    val instruction: String?,
    val severity: String?,
    @Serializable(DateSerializer::class) val eventEndingTime: Date?,
)
