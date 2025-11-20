package org.breezyweather.sources.meteoam.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MeteoAmReverseLocation(
    val city: String,
    val county: String?,
    val country: String,
    @SerialName("country_code") val countryCode: String?,
)
