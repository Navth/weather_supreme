package org.breezyweather.sources.geosphereat.json

import kotlinx.serialization.Serializable

@Serializable
data class GeoSphereAtHourlyProperties(
    val parameters: GeoSphereAtHourlyParameters?,
)
