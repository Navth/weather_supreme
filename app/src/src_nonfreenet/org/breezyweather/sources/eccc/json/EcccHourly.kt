package org.breezyweather.sources.eccc.json

import kotlinx.serialization.Serializable

@Serializable
data class EcccHourly(
    val epochTime: Int,
    val iconCode: String?,
    val condition: String?,

    val uv: EcccUv?,

    val temperature: EcccUnit?,
    val feelsLike: EcccUnit?,

    val windSpeed: EcccUnit?,
    val windGust: EcccUnit?,
    val windDir: String?,

    val precip: String?,
)
