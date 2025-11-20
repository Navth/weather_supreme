package org.breezyweather.sources.eccc.json

import kotlinx.serialization.Serializable

@Serializable
data class EcccDaily(
    val date: String?,
    val summary: String?,
    val periodLabel: String?,

    val iconCode: String?,
    val text: String?,

    val temperature: EcccDailyTemperature?,

    val precip: String?,

    val sun: EcccSun?,
)
