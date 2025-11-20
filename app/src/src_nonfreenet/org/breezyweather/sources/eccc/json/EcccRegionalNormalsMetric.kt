package org.breezyweather.sources.eccc.json

import kotlinx.serialization.Serializable

@Serializable
data class EcccRegionalNormalsMetric(
    val highTemp: Int?,
    val lowTemp: Int?,
)
