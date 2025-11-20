package org.breezyweather.sources.hko.json

import kotlinx.serialization.Serializable

@Serializable
data class HkoCurrentResult(
    val RegionalWeather: HkoCurrentRegionalWeather? = null,
)
