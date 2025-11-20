package org.breezyweather.sources.metoffice.json

import kotlinx.serialization.Serializable
import org.breezyweather.common.serializer.DateUtcSerializer
import java.util.Date

@Serializable
data class MetOfficeGeometry(
    val type: String?,
    val coordinates: List<Double>?,
)

@Serializable
data class MetOfficeLocation(
    val name: String?,
)

@Serializable
data class MetOfficeFeatureProperties<T>(
    val location: MetOfficeLocation?,
    val requestPointDistance: Double?,
    @Serializable(with = DateUtcSerializer::class)
    val modelRunDate: Date?,
    val timeSeries: List<T>,
)

@Serializable
data class MetOfficeFeature<T>(
    val geometry: MetOfficeGeometry?,
    val properties: MetOfficeFeatureProperties<T>,
)

@Serializable
data class MetOfficeForecast<T>(
    val features: List<MetOfficeFeature<T>>,
)
