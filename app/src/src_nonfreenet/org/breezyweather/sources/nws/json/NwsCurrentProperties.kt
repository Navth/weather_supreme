package org.breezyweather.sources.nws.json

import kotlinx.serialization.Serializable
import org.breezyweather.common.serializer.DateSerializer
import java.util.Date

@Serializable
data class NwsCurrentProperties(
    val elevation: NwsCurrentValue?,
    @Serializable(DateSerializer::class) val timestamp: Date?,
    val textDescription: String?,
    val icon: String?,
    val temperature: NwsCurrentValue?,
    val dewpoint: NwsCurrentValue?,
    val windDirection: NwsCurrentValue?,
    val windSpeed: NwsCurrentValue?,
    val windGust: NwsCurrentValue?,
    val barometricPressure: NwsCurrentValue?,
    val seaLevelPressure: NwsCurrentValue?,
    val visibility: NwsCurrentValue?,
    val relativeHumidity: NwsCurrentValue?,
    val windChill: NwsCurrentValue?,
)
