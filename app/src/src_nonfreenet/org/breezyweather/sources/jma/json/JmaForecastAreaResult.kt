package org.breezyweather.sources.jma.json

import kotlinx.serialization.Serializable

@Serializable
data class JmaForecastAreaResult(
    val class10: String,
    val amedas: List<String>,
)
