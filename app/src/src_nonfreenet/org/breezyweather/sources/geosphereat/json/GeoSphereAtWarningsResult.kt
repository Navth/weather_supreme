package org.breezyweather.sources.geosphereat.json

import kotlinx.serialization.Serializable

@Serializable
data class GeoSphereAtWarningsResult(
    val properties: GeoSphereAtWarningsProperties? = null,
)
