package org.breezyweather.sources.bmd.json

import kotlinx.serialization.Serializable

@Serializable
data class BmdForecastResult(
    val data: Map<String, BmdData>? = null,
)
