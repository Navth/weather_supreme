package org.breezyweather.sources.meteoam.json

import kotlinx.serialization.Serializable
import org.breezyweather.sources.meteoam.serializers.MeteoAmAnySerializer

@Serializable
data class MeteoAmObservationResult(
    val paramlist: List<String>? = null,
    val timeseries: List<List<String>>? = null,
    @Suppress("ktlint")
    val datasets: Map<String, Map<String, Map<String, @Serializable(with = MeteoAmAnySerializer::class) Any?>>>? = null,
)
