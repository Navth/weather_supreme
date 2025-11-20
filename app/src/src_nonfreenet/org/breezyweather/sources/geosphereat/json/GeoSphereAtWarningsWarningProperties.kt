package org.breezyweather.sources.geosphereat.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GeoSphereAtWarningsWarningProperties(
    val warnid: Int,
    val text: String?,
    @SerialName("auswirkungen") val consequences: String?,
    @SerialName("empfehlungen") val instructions: String?,
    val meteotext: String?,
    val rawinfo: GeoSphereAtWarningsWarningPropertiesRawInfo?,
)
