package org.breezyweather.sources.geosphereat.json

import kotlinx.serialization.Serializable

@Serializable
data class GeoSphereAtHourlyFeature(
    val properties: GeoSphereAtHourlyProperties?,
)
