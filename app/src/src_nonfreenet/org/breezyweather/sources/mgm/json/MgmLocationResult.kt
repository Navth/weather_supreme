package org.breezyweather.sources.mgm.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MgmLocationResult(
    @SerialName("boylam") val longitude: Double,
    @SerialName("enlem") val latitude: Double,
    @SerialName("gunlukTahminIstNo") val dailyStationId: Int,
    @SerialName("il") val province: String,
    @SerialName("ilce") val district: String?,
    @SerialName("merkezId") val currentStationId: Int,
    @SerialName("saatlikTahminIstNo") val hourlyStationId: Int?,
)
