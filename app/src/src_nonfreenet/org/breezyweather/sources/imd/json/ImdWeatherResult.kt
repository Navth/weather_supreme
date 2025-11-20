package org.breezyweather.sources.imd.json

import kotlinx.serialization.Serializable
import org.breezyweather.sources.imd.serializers.ImdAnySerializer

@Serializable
@Suppress("ktlint")
data class ImdWeatherResult(
    val apcp: List<@Serializable(ImdAnySerializer::class) Any?>? = null,
    val temp: List<@Serializable(ImdAnySerializer::class) Any?>? = null,
    val wspd: List<@Serializable(ImdAnySerializer::class) Any?>? = null,
    val wdir: List<@Serializable(ImdAnySerializer::class) Any?>? = null,
    val rh: List<@Serializable(ImdAnySerializer::class) Any?>? = null,
    val tcdc: List<@Serializable(ImdAnySerializer::class) Any?>? = null,
    val gust: List<@Serializable(ImdAnySerializer::class) Any?>? = null,
)
