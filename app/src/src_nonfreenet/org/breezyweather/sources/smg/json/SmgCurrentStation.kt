package org.breezyweather.sources.smg.json

import kotlinx.serialization.Serializable

@Serializable
data class SmgCurrentStation(
    val stationname: List<String>?,
    val Temperature: List<SmgValue>?,
    val Humidity: List<SmgValue>?,
    val DewPoint: List<SmgValue>?,
    val WindGust: List<SmgValue>?,
    val WindSpeed: List<SmgValue>?,
    val WindDirection: List<SmgValue>?,
    val MeanSeaLevelPressure: List<SmgValue>?,
)
