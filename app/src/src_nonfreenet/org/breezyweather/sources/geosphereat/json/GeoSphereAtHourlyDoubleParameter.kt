package org.breezyweather.sources.geosphereat.json

import kotlinx.serialization.Serializable

@Serializable
data class GeoSphereAtHourlyDoubleParameter(
    val data: Array<Double?>?,
)
