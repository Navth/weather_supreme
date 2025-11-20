package org.breezyweather.sources.eccc.json

import kotlinx.serialization.Serializable

@Serializable
data class EcccRegionalNormals(
    val metric: EcccRegionalNormalsMetric?,
)
