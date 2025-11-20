package org.breezyweather.sources.eccc.json

import kotlinx.serialization.Serializable
import org.breezyweather.sources.eccc.serializers.EcccEpochTimeSerializer

@Serializable(with = EcccEpochTimeSerializer::class)
data class EcccEpochTime(
    val epochTimeRounded: Int?,
)
