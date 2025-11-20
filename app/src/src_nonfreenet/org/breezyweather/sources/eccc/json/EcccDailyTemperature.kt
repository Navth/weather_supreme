package org.breezyweather.sources.eccc.json

import kotlinx.serialization.Serializable

@Serializable
data class EcccDailyTemperature(
    val periodHigh: Int?,
    val periodLow: Int?,
)
