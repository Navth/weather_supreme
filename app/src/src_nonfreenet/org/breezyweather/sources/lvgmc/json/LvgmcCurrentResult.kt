package org.breezyweather.sources.lvgmc.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LvgmcCurrentResult(
    @SerialName("laiks") val time: String?,
    @SerialName("stacijas_kods") val stationCode: String?,
    @SerialName("temperatura") val temperature: String?,
    @SerialName("veja_virziens") val windDirection: String?,
    @SerialName("videja_veja_atrums") val windSpeed: String?,
    @SerialName("veja_brazmas") val windGusts: String?,
    @SerialName("relativais_mitrums") val relativeHumidity: String?,
    @SerialName("atmosferas_spiediens") val pressure: String?,
    @SerialName("redzamiba") val visibility: String?,
    @SerialName("uvi") val uvIndex: String?,
)
