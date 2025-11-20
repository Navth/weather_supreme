package org.breezyweather.sources.eccc.json

import kotlinx.serialization.Serializable

@Serializable
data class EcccUnit(
    val metric: String?,
    val metricUnrounded: String?,
)
