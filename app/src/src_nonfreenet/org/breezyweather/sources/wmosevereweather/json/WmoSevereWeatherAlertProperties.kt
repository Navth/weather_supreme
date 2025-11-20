package org.breezyweather.sources.wmosevereweather.json

import kotlinx.serialization.Serializable
import org.breezyweather.common.serializer.DateSerializer
import java.util.Date

@Serializable
data class WmoSevereWeatherAlertProperties(
    val capurl: String?,
    val rlink: String?, // Alternative cap URL for other languages
    val identifier: String?,
    @Serializable(DateSerializer::class) val sent: Date?,
    val description: String?,
    val event: String?,
    val s: Int?,
    @Serializable(DateSerializer::class) val effective: Date?,
    @Serializable(DateSerializer::class) val onset: Date?,
    val url: String?,
    @Serializable(DateSerializer::class) val expires: Date?,
)
