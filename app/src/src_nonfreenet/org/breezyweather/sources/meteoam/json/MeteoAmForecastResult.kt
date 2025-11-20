package org.breezyweather.sources.meteoam.json

import kotlinx.serialization.Serializable
import org.breezyweather.common.serializer.DateSerializer
import java.util.Date

@Serializable
data class MeteoAmForecastResult(
    @Suppress("ktlint") val timeseries: List<@Serializable(DateSerializer::class) Date>? = null,
    val paramlist: List<String>? = null,
    val extrainfo: MeteoAmForecastExtraInfo? = null,
    val datasets: MeteoAmForecastDatasets? = null,
)
