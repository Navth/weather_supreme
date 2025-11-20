package org.breezyweather.sources.jma.json

import kotlinx.serialization.Serializable

@Serializable
data class JmaCurrentResult(
    val normalPressure: List<Double>? = null,
    val temp: List<Double>? = null,
    val humidity: List<Double>? = null,
    val windDirection: List<Int>? = null,
    val wind: List<Double>? = null,
    val visibility: List<Double>? = null,
    val weather: List<Int>? = null,
)
