package org.breezyweather.sources.geosphereat.json

import kotlinx.serialization.Serializable

@Serializable
data class GeoSphereAtWarningsWarning(
    val properties: GeoSphereAtWarningsWarningProperties,
)
