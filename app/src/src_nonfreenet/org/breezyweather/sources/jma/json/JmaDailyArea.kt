package org.breezyweather.sources.jma.json

import kotlinx.serialization.Serializable

@Serializable
data class JmaDailyArea(
    val area: JmaDailyAreaArea,
    val weatherCodes: List<String>?,
    val weathers: List<String>?,
    val pops: List<String>?,
    val temps: List<String>?,
    val tempsMin: List<String>?,
    val tempsMax: List<String>?,
    val min: String?,
    val max: String?,
)
