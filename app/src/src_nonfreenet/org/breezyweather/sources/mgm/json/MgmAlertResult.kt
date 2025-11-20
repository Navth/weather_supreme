package org.breezyweather.sources.mgm.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.breezyweather.common.serializer.DateSerializer
import java.util.Date

@Serializable
data class MgmAlertResult(
    @SerialName("_id") val id: String,
    val text: MgmAlertText?,
    val weather: MgmAlertWeather?,
    val towns: MgmAlertTowns?,
    @Serializable(DateSerializer::class) val begin: Date?,
    @Serializable(DateSerializer::class) val end: Date?,
)
