package org.breezyweather.sources.china.json

import kotlinx.serialization.Serializable
import org.breezyweather.common.serializer.DateSerializer
import java.util.Date

@Serializable
data class ChinaCurrent(
    @Serializable(DateSerializer::class) val pubTime: Date,
    val feelsLike: ChinaUnitValue?,
    val humidity: ChinaUnitValue?,
    val pressure: ChinaUnitValue?,
    val temperature: ChinaUnitValue?,
    val uvIndex: String?,
    val visibility: ChinaUnitValue?,
    val weather: String?,
    val wind: ChinaCurrentWind?,
)
