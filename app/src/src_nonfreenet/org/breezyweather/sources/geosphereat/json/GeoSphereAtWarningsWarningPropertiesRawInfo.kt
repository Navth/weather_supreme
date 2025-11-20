package org.breezyweather.sources.geosphereat.json

import kotlinx.serialization.Serializable

@Serializable
data class GeoSphereAtWarningsWarningPropertiesRawInfo(
    val wlevel: Int,
    val start: String?,
    val end: String?,
)
