package org.breezyweather.sources.nws.json

import kotlinx.serialization.Serializable

@Serializable
data class NwsPointProperties(
    val gridId: String,
    val gridX: Int,
    val gridY: Int,
    val relativeLocation: NwsPointLocation?,
    val forecastZone: String?,
    val timeZone: String,
)
