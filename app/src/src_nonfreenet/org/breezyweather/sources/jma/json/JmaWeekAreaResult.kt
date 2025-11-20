package org.breezyweather.sources.jma.json

import kotlinx.serialization.Serializable

@Serializable
data class JmaWeekAreaResult(
    val week: String,
    val amedas: String,
)
