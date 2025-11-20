package org.breezyweather.sources.nws.json

import kotlinx.serialization.Serializable
import org.breezyweather.common.serializer.DateSerializer
import java.util.Date

@Serializable
data class NwsDailyPeriods(
    @Serializable(DateSerializer::class) val startTime: Date,
    val isDaytime: Boolean,
    val temperature: NwsCurrentValue?,
    val probabilityOfPrecipitation: NwsCurrentValue?,
    val windSpeed: NwsCurrentValue?,
    val windGust: NwsCurrentValue?,
    val windDirection: String?,
    val icon: String?,
    val shortForecast: String?,
)
