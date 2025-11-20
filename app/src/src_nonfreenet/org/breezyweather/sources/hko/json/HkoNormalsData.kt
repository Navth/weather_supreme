package org.breezyweather.sources.hko.json

import kotlinx.serialization.Serializable

@Serializable
data class HkoNormalsData(
    val code: String?,
    val monData: List<List<String?>?>?,
)
