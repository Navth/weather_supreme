package org.breezyweather.sources.eccc.json

import kotlinx.serialization.Serializable
import org.breezyweather.sources.eccc.serializers.EcccSunSerializer

@Serializable(with = EcccSunSerializer::class)
data class EcccSun(
    val value: String?,
    // val unit: String?, // Always "hours"
)
