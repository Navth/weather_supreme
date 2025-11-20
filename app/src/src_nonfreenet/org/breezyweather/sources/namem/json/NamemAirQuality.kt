package org.breezyweather.sources.namem.json

import kotlinx.serialization.Serializable

@Serializable
data class NamemAirQuality(
    val id: String,
    val unit: String?,
    val current: Double?,
)
