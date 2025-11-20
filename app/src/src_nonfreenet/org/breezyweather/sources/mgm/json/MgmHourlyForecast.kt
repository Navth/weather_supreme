package org.breezyweather.sources.mgm.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MgmHourlyForecast(
    // Do not use @Serializable(DateSerializer::class) for "tarih".
    // The timestamp is in actually Europe/Istanbul, not Etc/UTC.
    // The 'Z' at the end of the timestamp is misused.
    @SerialName("tarih") val time: String,
    @SerialName("hadise") val condition: String?,
    @SerialName("sicaklik") val temperature: Double?,
    @SerialName("nem") val humidity: Double?,
    @SerialName("ruzgarYonu") val windDirection: Double?,
    @SerialName("ruzgarHizi") val windSpeed: Double?,
    @SerialName("maksimumRuzgarHizi") val gust: Double?,
)
