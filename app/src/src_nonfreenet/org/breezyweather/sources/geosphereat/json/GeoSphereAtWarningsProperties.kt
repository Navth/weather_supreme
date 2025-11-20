package org.breezyweather.sources.geosphereat.json

import kotlinx.serialization.Serializable

@Serializable
data class GeoSphereAtWarningsProperties(
    val warnings: List<GeoSphereAtWarningsWarning>?,
)
