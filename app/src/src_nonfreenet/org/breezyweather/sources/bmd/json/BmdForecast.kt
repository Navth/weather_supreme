package org.breezyweather.sources.bmd.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BmdForecast(
    @SerialName("step_start") val stepStart: String,
    @SerialName("val_min") val valMin: Double?,
    @SerialName("val_avg") val valAvg: Double?,
    @SerialName("val_max") val valMax: Double?,
    @SerialName("val_avg_day") val valAvgDay: Double?,
    @SerialName("val_avg_night") val valAvgNight: Double?,
)
