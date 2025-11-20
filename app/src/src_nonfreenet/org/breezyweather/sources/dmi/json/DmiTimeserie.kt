package org.breezyweather.sources.dmi.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.breezyweather.common.serializer.DateSerializer
import java.util.Date

@Serializable
data class DmiTimeserie(
    @Serializable(DateSerializer::class) val localTimeIso: Date,
    val temp: Double?,
    val symbol: Int?,
    @SerialName("precip1") val precip: Double?,
    val windDegree: Double?,
    val windSpeed: Double?,
    val windGust: Double?,
    val humidity: Double?,
    val pressure: Double?,
    val visibility: Double?,
)
