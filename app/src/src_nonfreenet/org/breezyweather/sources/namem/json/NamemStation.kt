package org.breezyweather.sources.namem.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NamemStation(
    @SerialName("aimag_name") val provinceName: String?,
    @SerialName("sum_name") val districtName: String?,
    @SerialName("sta_name") val stationName: String?,
    val id: Long?,
    val sid: Long?,
    val lat: Double?,
    val lon: Double?,
    val elementList: List<NamemAirQuality>?,
)
