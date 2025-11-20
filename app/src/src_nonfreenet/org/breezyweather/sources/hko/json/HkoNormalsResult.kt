package org.breezyweather.sources.hko.json

import kotlinx.serialization.Serializable

@Serializable
data class HkoNormalsResult(
    val stn: HkoNormalsStn? = null,
)
