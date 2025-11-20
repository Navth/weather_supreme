package org.breezyweather.sources.hko.json

import kotlinx.serialization.Serializable

@Serializable
data class HkoNormalsStn(
    val data: List<HkoNormalsData>,
)
