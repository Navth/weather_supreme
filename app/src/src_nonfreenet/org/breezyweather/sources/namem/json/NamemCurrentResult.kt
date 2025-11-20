package org.breezyweather.sources.namem.json

import kotlinx.serialization.Serializable

@Serializable
data class NamemCurrentResult(
    val aws: List<NamemCurrent>? = null,
)
