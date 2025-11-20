package org.breezyweather.sources.climweb.json

import kotlinx.serialization.Serializable
import org.breezyweather.sources.climweb.serializers.ClimWebAnySerializer

@Serializable
data class ClimWebAlertsResult(
    // Instead of reinventing the wheel to serialize GeoJson features,
    // we can just dump the entire JSON property as a String,
    // and reconstruct the GeoJson object using GeoJsonParser.
    val features:
    @Serializable(with = ClimWebAnySerializer::class)
    Any? = null,
)
