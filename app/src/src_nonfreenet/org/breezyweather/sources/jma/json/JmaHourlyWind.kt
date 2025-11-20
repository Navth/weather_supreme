package org.breezyweather.sources.jma.json

import kotlinx.serialization.Serializable

@Serializable
data class JmaHourlyWind(
    val direction: String?,
    val range: String?,
)
