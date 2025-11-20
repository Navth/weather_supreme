package org.breezyweather.sources.lvgmc.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LvgmcForecastResult(
    @SerialName("punkts") val point: String?,
    @SerialName("nosaukums") val name: String?,
    @SerialName("novads") val municipality: String?,
    @SerialName("lon") val longitude: String?,
    @SerialName("lat") val latitude: String?,
    @SerialName("laiks") val time: String?,
    @SerialName("temperatura") val temperature: String?,
    @SerialName("veja_atrums") val windSpeed: String?,
    @SerialName("veja_virziens") val windDirection: String?,
    @SerialName("brazmas") val windGusts: String?,
    @SerialName("nokrisni_1h") val precipitation1h: String?,
    @SerialName("nokrisni_12h") val precipitation12h: String?,
    @SerialName("relativais_mitrums") val relativeHumidity: String?,
    @SerialName("laika_apstaklu_ikona") val icon: String?,
    @SerialName("spiediens") val pressure: String?,
    @SerialName("sajutu_temperatura") val apparentTemperature: String?,
    @SerialName("sniegs") val snow: String?,
    @SerialName("makoni") val cloudCover: String?,
    @SerialName("nokrisnu_varbutiba") val precipitationProbability: String?,
    @SerialName("uvi_indekss") val uvIndex: String?,
    @SerialName("perkons") val thunderstormProbability: String?,
)
