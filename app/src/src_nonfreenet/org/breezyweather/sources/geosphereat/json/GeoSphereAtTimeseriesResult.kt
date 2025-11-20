package org.breezyweather.sources.geosphereat.json

import kotlinx.serialization.Serializable
import org.breezyweather.common.serializer.DateSerializer
import java.util.Date

@Serializable
data class GeoSphereAtTimeseriesResult(
    @Suppress("ktlint") val timestamps: List<@Serializable(DateSerializer::class) Date>? = null,
    val features: List<GeoSphereAtHourlyFeature>? = null,
)
