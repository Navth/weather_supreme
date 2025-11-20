package org.breezyweather.sources.meteoam.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.breezyweather.sources.meteoam.serializers.MeteoAmAnySerializer

@Suppress("ktlint")
@Serializable
data class MeteoAmForecastDatasets(
    @SerialName("0") val data: Map<String, Map<String, @Serializable(with = MeteoAmAnySerializer::class) Any?>>?,
)
