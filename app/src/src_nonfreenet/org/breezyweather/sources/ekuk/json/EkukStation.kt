package org.breezyweather.sources.ekuk.json

import kotlinx.serialization.Serializable

@Serializable
data class EkukStation(
    val id: Long,
    val geometry: EkukStationGeometry,
    val properties: EkukStationProperties,
)
