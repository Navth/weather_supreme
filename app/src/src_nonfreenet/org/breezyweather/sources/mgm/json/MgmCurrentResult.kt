package org.breezyweather.sources.mgm.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MgmCurrentResult(
    @SerialName("denizeIndirgenmisBasinc") val pressure: Double?,
    @SerialName("hadiseKodu") val condition: String?,
    @SerialName("nem") val humidity: Double?,
    @SerialName("ruzgarHiz") val windSpeed: Double?,
    @SerialName("ruzgarYon") val windDirection: Double?,
    @SerialName("sicaklik") val temperature: Double?,
)
