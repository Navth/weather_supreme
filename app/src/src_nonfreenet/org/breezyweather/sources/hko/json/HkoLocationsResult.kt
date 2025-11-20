package org.breezyweather.sources.hko.json

import kotlinx.serialization.Serializable
import org.breezyweather.sources.hko.serializers.HkoAnySerializer

@Serializable
data class HkoLocationsResult(
    // Instead of reinventing the wheel to serialize GeoJson features,
    // we can just dump the entire JSON property as a String,
    // and reconstruct the GeoJson object using GeoJsonParser.
    @Serializable(with = HkoAnySerializer::class)
    val features: Any?,
)
