package org.breezyweather.sources.meteoam.json

import kotlinx.serialization.Serializable
import org.breezyweather.common.serializer.DateSerializer
import java.util.Date

@Serializable
data class MeteoAmForecastStats(
    @Serializable(DateSerializer::class) val localDate: Date,
    val icon: String?,
)
