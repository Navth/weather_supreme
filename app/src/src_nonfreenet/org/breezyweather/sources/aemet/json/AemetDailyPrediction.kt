package org.breezyweather.sources.aemet.json

import kotlinx.serialization.Serializable

@Serializable
data class AemetDailyPrediction(
    val dia: List<AemetDailyDay>?,
)
