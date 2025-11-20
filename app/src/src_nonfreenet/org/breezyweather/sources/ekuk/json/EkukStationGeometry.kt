package org.breezyweather.sources.ekuk.json

import kotlinx.serialization.Serializable

@Serializable
data class EkukStationGeometry(
    val coordinates: List<Double>,
)
