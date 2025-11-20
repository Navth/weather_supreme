package org.breezyweather.sources.namem.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NamemCurrent(
    val ttt: Double?,
    val ff: Double?,
    val pslp: Double?,
    @SerialName("wind_dir") val windDir: Double?,
    @SerialName("wind_speed") val windSpeed: Double?,
    @SerialName("ttt_feels") val tttFeels: Double?,
    @SerialName("Nh") val nh: Int?,
)
