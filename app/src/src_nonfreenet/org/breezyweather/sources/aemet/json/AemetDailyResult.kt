package org.breezyweather.sources.aemet.json

import kotlinx.serialization.Serializable

@Serializable
data class AemetDailyResult(
    val prediccion: AemetDailyPrediction? = null,
)
