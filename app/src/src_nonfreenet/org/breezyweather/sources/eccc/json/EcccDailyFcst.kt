package org.breezyweather.sources.eccc.json

import kotlinx.serialization.Serializable

@Serializable
data class EcccDailyFcst(
    val daily: List<EcccDaily>?,
    val dailyIssuedTimeEpoch: String?,
    val regionalNormals: EcccRegionalNormals?,
)
