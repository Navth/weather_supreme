package org.breezyweather.sources.jma.json

import kotlinx.serialization.Serializable
import org.breezyweather.sources.jma.serializers.JmaAnySerializer

@Serializable
data class JmaClass20sResult(
    // Instead of reinventing the wheel to serialize GeoJson features,
    // we can just dump the entire JSON property as a String,
    // and reconstruct the GeoJson object using GeoJsonParser.
    @Suppress("ktlint")
    val features: List<@Serializable(with = JmaAnySerializer::class) Any?> = listOf(),
)
