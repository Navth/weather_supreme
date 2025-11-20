package org.breezyweather.sources.nws.json

import kotlinx.serialization.Serializable
import org.breezyweather.common.serializer.DateSerializer
import java.util.Date

@Serializable
data class NwsAlertProperties(
    val id: String,
    @Serializable(DateSerializer::class) val effective: Date?,
    @Serializable(DateSerializer::class) val onset: Date?,
    @Serializable(DateSerializer::class) val expires: Date?,
    val status: String?,
    val severity: String?,
    val event: String?,
    val headline: String?,
    val description: String?,
    val instruction: String?,
    val sender: String?,
    val senderName: String?,
)
