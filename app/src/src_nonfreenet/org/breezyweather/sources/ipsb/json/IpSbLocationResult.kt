package org.breezyweather.sources.ipsb.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IpSbLocationResult(
    val longitude: Double,
    val latitude: Double,
    val timezone: String?,
    val city: String?,
    val region: String?,
    @SerialName("region_code") val regionCode: String?,
    val country: String?,
    @SerialName("country_code") val countryCode: String?,
)
