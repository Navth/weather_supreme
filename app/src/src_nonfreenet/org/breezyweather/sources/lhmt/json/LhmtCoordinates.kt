package org.breezyweather.sources.lhmt.json

import kotlinx.serialization.Serializable

@Serializable
data class LhmtCoordinates(
    val latitude: Double,
    val longitude: Double,
)
