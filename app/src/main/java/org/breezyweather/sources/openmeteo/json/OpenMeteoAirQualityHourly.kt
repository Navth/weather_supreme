package org.breezyweather.sources.openmeteo.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OpenMeteoAirQualityHourly(
    val time: LongArray,
    val pm10: Array<Double?>?,
    @SerialName("pm2_5") val pm25: Array<Double?>?,
    @SerialName("carbon_monoxide") val carbonMonoxide: Array<Double?>?,
    @SerialName("nitrogen_dioxide") val nitrogenDioxide: Array<Double?>?,
    @SerialName("sulphur_dioxide") val sulphurDioxide: Array<Double?>?,
    val ozone: Array<Double?>?,
    @SerialName("alder_pollen") val alderPollen: Array<Double?>?,
    @SerialName("birch_pollen") val birchPollen: Array<Double?>?,
    @SerialName("grass_pollen") val grassPollen: Array<Double?>?,
    @SerialName("mugwort_pollen") val mugwortPollen: Array<Double?>?,
    @SerialName("olive_pollen") val olivePollen: Array<Double?>?,
    @SerialName("ragweed_pollen") val ragweedPollen: Array<Double?>?,
)
