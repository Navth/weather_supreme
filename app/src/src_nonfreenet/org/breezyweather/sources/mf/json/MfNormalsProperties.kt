package org.breezyweather.sources.mf.json

import kotlinx.serialization.Serializable

@Serializable
data class MfNormalsProperties(
    val stats: List<MfNormalsStats>?,
)
