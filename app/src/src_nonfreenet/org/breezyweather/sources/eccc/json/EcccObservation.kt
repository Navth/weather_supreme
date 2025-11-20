package org.breezyweather.sources.eccc.json

import kotlinx.serialization.Serializable

@Serializable
data class EcccObservation(
    val iconCode: String?,
    val condition: String?,

    val temperature: EcccUnit?,
    val feelsLike: EcccUnit?,

    val dewpoint: EcccUnit?,
    val humidity: String?,

    val visibility: EcccUnit?,
    val pressure: EcccUnit?,

    val windSpeed: EcccUnit?,
    val windGust: EcccUnit?,
    val windBearing: String?,
)
