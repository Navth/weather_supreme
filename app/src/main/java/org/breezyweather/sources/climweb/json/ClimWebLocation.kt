package org.breezyweather.sources.climweb.json

import kotlinx.serialization.Serializable

@Serializable
data class ClimWebLocation(
    val id: String?,
    val name: String?,
    val coordinates: List<Double>?,
    val slug: String?,
)
