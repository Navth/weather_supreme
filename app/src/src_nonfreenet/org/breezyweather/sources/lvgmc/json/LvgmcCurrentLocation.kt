package org.breezyweather.sources.lvgmc.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LvgmcCurrentLocation(
    @SerialName("kods") val code: String?,
    @SerialName("nosaukums") val name: String?,
    @SerialName("lon") val longitude: String?,
    @SerialName("lat") val latitude: String?,
)
