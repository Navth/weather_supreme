package org.breezyweather.sources.hko.json

import kotlinx.serialization.Serializable

@Serializable
data class HkoCurrentWind(
    val WindSpeed: String?,
    val Gust: String?,
    val WindDirectionCode: String?,
)
