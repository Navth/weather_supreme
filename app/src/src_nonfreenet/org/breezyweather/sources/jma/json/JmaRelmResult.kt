package org.breezyweather.sources.jma.json

import kotlinx.serialization.Serializable

@Serializable
data class JmaRelmResult(
    val ne: List<Double>,
    val sw: List<Double>,
)
