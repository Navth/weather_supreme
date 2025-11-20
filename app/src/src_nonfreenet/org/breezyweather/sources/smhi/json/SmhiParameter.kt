package org.breezyweather.sources.smhi.json

import kotlinx.serialization.Serializable

@Serializable
data class SmhiParameter(
    val name: String,
    val values: List<Double>,
)
