package org.breezyweather.sources.bmkg.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.breezyweather.common.serializer.DateSerializer
import java.util.Date

@Serializable
data class BmkgCuaca(
    @Serializable(DateSerializer::class) val datetime: Date?,
    val t: Double?,
    val tcc: Double?,
    val tp: Double?,
    val weather: Int?,
    @SerialName("wd_deg") val wdDeg: Double?,
    val ws: Double?,
    val hu: Double?,
    val vs: Double?,
)
