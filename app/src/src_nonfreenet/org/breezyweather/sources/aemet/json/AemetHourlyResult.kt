package org.breezyweather.sources.aemet.json

import kotlinx.serialization.Serializable

@Serializable
data class AemetHourlyResult(
    val prediccion: AemetHourlyPrediction? = null,
)
