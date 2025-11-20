package org.breezyweather.sources.jma.json

import kotlinx.serialization.Serializable

@Serializable
data class JmaAmedasResult(
    val elems: String,
    val lat: List<Double>,
    val lon: List<Double>,
    val alt: Double,
)
