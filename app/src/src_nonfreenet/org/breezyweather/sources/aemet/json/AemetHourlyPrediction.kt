package org.breezyweather.sources.aemet.json

import kotlinx.serialization.Serializable

@Serializable
data class AemetHourlyPrediction(
    val dia: List<AemetHourlyDay>?,
)
